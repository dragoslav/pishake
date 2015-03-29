package nl.proja.pishake.operation

import nl.proja.pishake.model.Gpio
import nl.proja.pishake.util.ActorReference

import scala.concurrent.duration.{FiniteDuration, _}
import scala.language.postfixOps

object GpioIn {

  trait InOperation {
    def pin: Gpio.Pin
  }

  case class ReadState(pin: Gpio.Pin) extends InOperation

  case class State(pin: Gpio.Pin, state: Gpio.State) extends InOperation

}

object GpioOut {

  trait OutOperation {
    def pin: Gpio.Pin
  }

  case class Low(pin: Gpio.Pin) extends OutOperation

  case class High(pin: Gpio.Pin) extends OutOperation

  case class Toggle(pin: Gpio.Pin) extends OutOperation

  case class Pulse(pin: Gpio.Pin, duration: FiniteDuration, state: Gpio.State = Gpio.State.High) extends OutOperation

  case class Blink(pin: Gpio.Pin, delay: FiniteDuration = 0 seconds, duration: FiniteDuration = 1 seconds, state: Gpio.State = Gpio.State.High) extends OutOperation

  case class SetState(pin: Gpio.Pin, state: Gpio.State) extends OutOperation

}

object GpioController extends ActorReference
