package nl.proja.pishake.actor

import akka.actor.{Actor, ActorLogging, Props}
import com.pi4j.io.gpio._
import com.pi4j.io.gpio.event.{GpioPinDigitalStateChangeEvent, GpioPinListenerDigital}
import nl.proja.pishake.actor.GpioConversion._
import nl.proja.pishake.model.Gpio
import nl.proja.pishake.operation.GpioIn
import nl.proja.pishake.operation.GpioIn.ReadState
import nl.proja.pishake.operation.GpioOut._
import nl.proja.pishake.util.{ActorDescription, ActorSupport}

import scala.collection.JavaConversions._
import scala.languageFeature.postfixOps

object GpioController extends ActorDescription {

  def props(args: Any*): Props = Props[GpioController]
}

class GpioController extends Actor with ActorLogging with ActorSupport {

  lazy val gpio = GpioFactory.getInstance

  def receive: Receive = {
    case Low(pin) => output(pin).low()

    case High(pin) => output(pin).high()

    case Toggle(pin) => output(pin).toggle()

    case Pulse(pin, duration, state) => output(pin).pulse(duration.toMillis, state)

    case Blink(pin, delay, duration, state) => output(pin).blink(duration.toMillis, state)

    case SetState(pin, state) => output(pin).setState(state)

    case ReadState(pin) => GpioConversion.asState(input(pin).getState)
  }

  def input(pin: Gpio.Pin): GpioPinDigitalInput = {

    def listen: (GpioPinDigitalInput => GpioPinDigitalInput) = { pi4jPin =>
      val receiver = sender()
      pi4jPin.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF)
      pi4jPin.addListener(new GpioPinListenerDigital {
        def handleGpioPinDigitalStateChangeEvent(event: GpioPinDigitalStateChangeEvent) {
          receiver ! GpioIn.State(pin, event.getState)
        }
      })
      pi4jPin
    }

    listen(gpio.getProvisionedPins.toList.find(_.getName == GpioConversion.asPin(pin).getName) match {
      case None => gpio.provisionDigitalInputPin(pin)
      case Some(pi4jPin) if pi4jPin.getMode == PinMode.DIGITAL_INPUT => pi4jPin.asInstanceOf[GpioPinDigitalInput]
      case Some(pi4jPin) =>
        gpio.unprovisionPin(pi4jPin)
        gpio.provisionDigitalInputPin(pin)
    })
  }


  def output(pin: Gpio.Pin): GpioPinDigitalOutput = gpio.getProvisionedPins.toList.find(_.getName == GpioConversion.asPin(pin).getName) match {
    case None => gpio.provisionDigitalOutputPin(pin)
    case Some(pi4jPin) if pi4jPin.getMode == PinMode.DIGITAL_OUTPUT => pi4jPin.asInstanceOf[GpioPinDigitalOutput]
    case Some(pi4jPin) =>
      gpio.unprovisionPin(pi4jPin)
      gpio.provisionDigitalOutputPin(pin)
  }
}
