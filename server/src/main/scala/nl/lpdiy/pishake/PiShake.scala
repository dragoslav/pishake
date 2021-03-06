package nl.lpdiy.pishake

import akka.actor.ActorSystem
import nl.lpdiy.pishake.actor.{DS18B20Controller, GpioController}
import nl.lpdiy.pishake.util.ActorSupport
import nl.lpdiy.pishake.actor.{DS18B20Controller, GpioController, SystemActor}
import nl.lpdiy.pishake.util.ActorSupport


object PiShake extends App {

  implicit val system = ActorSystem("PiShake")

  def run(implicit system: ActorSystem) = {
    ActorSupport.actorOf(SystemActor)
    ActorSupport.actorOf(GpioController)
    ActorSupport.actorOf(DS18B20Controller)
  }

  run
}
