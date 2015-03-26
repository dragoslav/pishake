package nl.proja.pishake.actor

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.pi4j.io.gpio._
import com.pi4j.io.gpio.event.{GpioPinDigitalStateChangeEvent, GpioPinListenerDigital}
import nl.proja.pishake.actor.GpioConversion._
import nl.proja.pishake.common.{ActorDescription, ActorSupport}
import nl.proja.pishake.model.Gpio
import nl.proja.pishake.operation.GpioController.{ProvisionDigitalInput, ProvisionDigitalOutput, Reset, UnProvision}
import nl.proja.pishake.operation.GpioIn
import nl.proja.pishake.operation.GpioOut._

import scala.collection.mutable
import scala.util.Try

object GpioController extends ActorDescription {

  def props(args: Any*): Props = Props[GpioController]
}

class GpioController extends Actor with ActorLogging with ActorSupport {

  lazy val gpio = GpioFactory.getInstance

  val pins = new mutable.LinkedHashMap[Gpio.Pin, GpioLink]

  def receive: Receive = {
    case ProvisionDigitalOutput(pin, state) => GpioConversion.asState(state) match {
      case None => Try(provision(pin, gpio.provisionDigitalOutputPin(pin)))
      case Some(s) => Try(provision(pin, gpio.provisionDigitalOutputPin(pin, s)))
    }

    case ProvisionDigitalInput(pin, resistance) => GpioConversion.asResistance(resistance) match {
      case None => Try(provision(pin, gpio.provisionDigitalInputPin(pin)))
      case Some(r) => Try(provision(pin, gpio.provisionDigitalInputPin(pin, r)))
    }

    case UnProvision(pin) => pins.remove(pin) match {
      case Some(link) => Try(gpio.unprovisionPin(link.pin))
      case _ =>
    }

    case Reset =>
      pins.values.foreach(link => Try(gpio.unprovisionPin(link.pin)))
      pins.clear()

    case Low(pin) => pins.get(pin) match {
      case Some(link) => link.pin match {
        case out: GpioPinDigitalOutput => Try(out.low())
        case _ =>
      }
      case _ =>
    }

    case High(pin) => pins.get(pin) match {
      case Some(link) => link.pin match {
        case out: GpioPinDigitalOutput => Try(out.high())
        case _ =>
      }
      case _ =>
    }

    case Toggle(pin) => pins.get(pin) match {
      case Some(link) => link.pin match {
        case out: GpioPinDigitalOutput => Try(out.toggle())
        case _ =>
      }
      case _ =>
    }

    case Pulse(pin, duration, state) => pins.get(pin) match {
      case Some(link) => link.pin match {
        case out: GpioPinDigitalOutput => Try(out.pulse(duration.toMillis, state))
        case _ =>
      }
      case _ =>
    }

    case Blink(pin, delay, duration, state) => pins.get(pin) match {
      case Some(link) => link.pin match {
        case out: GpioPinDigitalOutput => Try(out.blink(duration.toMillis, state))
        case _ =>
      }
      case _ =>
    }

    case State(pin, state) => pins.get(pin) match {
      case Some(link) => link.pin match {
        case out: GpioPinDigitalOutput => Try(out.setState(state))
        case _ =>
      }
      case _ =>
    }

  }

  def provision(pin: Gpio.Pin, pi4jPin: GpioPin) = {
    pins += pin -> GpioLink(pi4jPin, sender())

    if (pi4jPin.isInstanceOf[GpioPinDigital]) {
      pi4jPin.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF)
      pi4jPin.addListener(new GpioPinListenerDigital {
        def handleGpioPinDigitalStateChangeEvent(event: GpioPinDigitalStateChangeEvent) {
          pins.get(pin) match {
            case Some(link) => link.owner ! GpioIn.State(pin, event.getState)
            case _ =>
          }
        }
      })
    }
  }
}

case class GpioLink(pin: GpioPin, owner: ActorRef)