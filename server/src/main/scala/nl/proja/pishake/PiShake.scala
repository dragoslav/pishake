package nl.proja.pishake

import akka.actor.ActorSystem
import nl.proja.pishake.actor.{GpioController, SystemActor}
import nl.proja.pishake.util.ActorSupport


object PiShake extends App {

  implicit val system = ActorSystem("PiShake")

  ActorSupport.actorOf(SystemActor)
  ActorSupport.actorOf(GpioController)
}
