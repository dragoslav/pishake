package nl.proja.pishake.operation

import nl.proja.pishake.model.Gpio
import nl.proja.pishake.util.ActorReference

import scala.concurrent.duration.{FiniteDuration, _}
import scala.language.postfixOps

object GpioIn {

  trait PinInOperation {
    def pin: Gpio.Pin
  }

  case class ReadState(pin: Gpio.Pin) extends PinInOperation

  case class State(pin: Gpio.Pin, state: Gpio.State) extends PinInOperation

}

object GpioOut {

  trait PinOutOperation {
    def pin: Gpio.Pin
  }

  case class Low(pin: Gpio.Pin) extends PinOutOperation

  case class High(pin: Gpio.Pin) extends PinOutOperation

  case class Toggle(pin: Gpio.Pin) extends PinOutOperation

  case class Pulse(pin: Gpio.Pin, duration: FiniteDuration, state: Gpio.State = Gpio.State.High) extends PinOutOperation

  case class Blink(pin: Gpio.Pin, delay: FiniteDuration = 0 seconds, duration: FiniteDuration = 1 seconds, state: Gpio.State = Gpio.State.High) extends PinOutOperation

  case class SetState(pin: Gpio.Pin, state: Gpio.State) extends PinOutOperation

}

object GpioController extends ActorReference
