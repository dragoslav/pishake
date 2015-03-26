package nl.proja.pishake.model

object Gpio {

  sealed trait State

  object State {

    object High extends State

    object Low extends State

  }

  sealed trait PullResistance

  object PullResistance {

    object Off extends PullResistance

    object PullDown extends PullResistance

    object PullUp extends PullResistance

  }

  sealed trait Mode

  object Mode {

    object DigitalInput extends Mode

    object DigitalOutput extends Mode

    object AnalogInput extends Mode

    object AnalogOutput extends Mode

  }

  sealed trait Pin

  object Pin {

    object Pin00 extends Pin

    object Pin01 extends Pin

    object Pin02 extends Pin

    object Pin03 extends Pin

    object Pin04 extends Pin

    object Pin05 extends Pin

    object Pin06 extends Pin

    object Pin07 extends Pin

    object Pin08 extends Pin

    object Pin09 extends Pin

    object Pin10 extends Pin

    object Pin11 extends Pin

    object Pin12 extends Pin

    object Pin13 extends Pin

    object Pin14 extends Pin

    object Pin15 extends Pin

    object Pin16 extends Pin

    object Pin17 extends Pin

    object Pin18 extends Pin

    object Pin19 extends Pin

    object Pin20 extends Pin

    object Pin21 extends Pin

    object Pin22 extends Pin

    object Pin23 extends Pin

    object Pin24 extends Pin

    object Pin25 extends Pin

    object Pin26 extends Pin

    object Pin27 extends Pin

    object Pin28 extends Pin

    object Pin29 extends Pin

  }

}
