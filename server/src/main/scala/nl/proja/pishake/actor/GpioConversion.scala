package nl.proja.pishake.actor

import com.pi4j.io.gpio.{Pin, PinPullResistance, PinState, RaspiPin}
import nl.proja.pishake.model.Gpio

import scala.language.implicitConversions

object GpioConversion {
  implicit def asPin(pin: Gpio.Pin): Pin = RaspiPin.getPinByName(s"GPIO ${pin.getClass.getSimpleName.substring(3).toInt}")

  implicit def asState(state: Option[Gpio.State]): Option[PinState] = state match {
    case None => None
    case Some(s) => PinState.allStates().toList.find(_.name == s.getClass.getSimpleName.toUpperCase)
  }

  implicit def asState(state: Gpio.State): PinState = PinState.allStates().toList.find(_.name == state.getClass.getSimpleName.toUpperCase).get

  implicit def asState(state: PinState): Gpio.State = state match {
    case PinState.LOW => Gpio.State.Low
    case PinState.HIGH => Gpio.State.High
  }

  implicit def asResistance(resistance: Option[Gpio.PullResistance]): Option[PinPullResistance] = resistance match {
    case None => None
    case Some(r) => r match {
      case Gpio.PullResistance.Off => Some(PinPullResistance.OFF)
      case Gpio.PullResistance.PullUp => Some(PinPullResistance.PULL_UP)
      case Gpio.PullResistance.PullDown => Some(PinPullResistance.PULL_DOWN)
      case _ => None
    }
  }
}
