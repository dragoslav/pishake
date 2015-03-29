package nl.proja.pishake

import akka.actor.ActorSystem
import nl.proja.pishake.actor.{DS18B20Controller, GpioController, SystemActor}
import nl.proja.pishake.util.ActorSupport


object PiShake extends App {

  implicit val system = ActorSystem("PiShake")

  ActorSupport.actorOf(SystemActor)
  ActorSupport.actorOf(GpioController)
  ActorSupport.actorOf(DS18B20Controller)
}
