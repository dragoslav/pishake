package nl.proja.pishake.operation

import nl.proja.pishake.common.ActorReference
import nl.proja.pishake.model.Gpio

import scala.concurrent.duration.{FiniteDuration, _}
import scala.language.postfixOps

object GpioIn {

  trait PinInOperation {
    def pin: Gpio.Pin
  }

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

  case class Blink(pin: Gpio.Pin, delay: FiniteDuration, duration: FiniteDuration = 0 milliseconds, state: Gpio.State = Gpio.State.High) extends PinOutOperation

  case class State(pin: Gpio.Pin, state: Gpio.State) extends PinOutOperation

}

object GpioController extends ActorReference {

  case class ProvisionAnalogOutput(pin: Gpio.Pin, state: Option[Gpio.State] = None)

  case class ProvisionDigitalOutput(pin: Gpio.Pin, state: Option[Gpio.State] = None)

  case class ProvisionAnalogInput(pin: Gpio.Pin)

  case class ProvisionDigitalInput(pin: Gpio.Pin, resistance: Option[Gpio.PullResistance] = None)

  case class UnProvision(pin: Gpio.Pin)

  object Reset

}
