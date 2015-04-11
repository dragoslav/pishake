package nl.lpdiy.pishake.actor

import com.pi4j.io.gpio.{Pin, PinPullResistance, PinState, RaspiPin}
import nl.lpdiy.pishake.model.Gpio

import scala.language.implicitConversions

object GpioConversion {
  implicit def asPin(pin: Gpio.Pin): Pin = pin match {
    case Gpio.Pin.Pin00 => RaspiPin.GPIO_00

    case Gpio.Pin.Pin01 => RaspiPin.GPIO_01

    case Gpio.Pin.Pin02 => RaspiPin.GPIO_02

    case Gpio.Pin.Pin03 => RaspiPin.GPIO_03

    case Gpio.Pin.Pin04 => RaspiPin.GPIO_04

    case Gpio.Pin.Pin05 => RaspiPin.GPIO_05

    case Gpio.Pin.Pin06 => RaspiPin.GPIO_06

    case Gpio.Pin.Pin07 => RaspiPin.GPIO_07

    case Gpio.Pin.Pin08 => RaspiPin.GPIO_08

    case Gpio.Pin.Pin09 => RaspiPin.GPIO_09

    case Gpio.Pin.Pin10 => RaspiPin.GPIO_10

    case Gpio.Pin.Pin11 => RaspiPin.GPIO_11

    case Gpio.Pin.Pin12 => RaspiPin.GPIO_12

    case Gpio.Pin.Pin13 => RaspiPin.GPIO_13

    case Gpio.Pin.Pin14 => RaspiPin.GPIO_14

    case Gpio.Pin.Pin15 => RaspiPin.GPIO_15

    case Gpio.Pin.Pin16 => RaspiPin.GPIO_16

    case Gpio.Pin.Pin17 => RaspiPin.GPIO_17

    case Gpio.Pin.Pin18 => RaspiPin.GPIO_18

    case Gpio.Pin.Pin19 => RaspiPin.GPIO_19

    case Gpio.Pin.Pin20 => RaspiPin.GPIO_20

    case Gpio.Pin.Pin21 => RaspiPin.GPIO_21

    case Gpio.Pin.Pin22 => RaspiPin.GPIO_22

    case Gpio.Pin.Pin23 => RaspiPin.GPIO_23

    case Gpio.Pin.Pin24 => RaspiPin.GPIO_24

    case Gpio.Pin.Pin25 => RaspiPin.GPIO_25

    case Gpio.Pin.Pin26 => RaspiPin.GPIO_26

    case Gpio.Pin.Pin27 => RaspiPin.GPIO_27

    case Gpio.Pin.Pin28 => RaspiPin.GPIO_28

    case Gpio.Pin.Pin29 => RaspiPin.GPIO_29
  }

  implicit def asState(state: Option[Gpio.State]): Option[PinState] = state match {
    case Some(Gpio.State.Low) => Some(PinState.LOW)
    case Some(Gpio.State.High) => Some(PinState.HIGH)
    case _ => None
  }

  implicit def asState(state: Gpio.State): PinState = state match {
    case Gpio.State.Low => PinState.LOW
    case Gpio.State.High => PinState.HIGH
  }

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
