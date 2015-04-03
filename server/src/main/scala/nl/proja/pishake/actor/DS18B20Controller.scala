package nl.proja.pishake.actor

import akka.actor.{Actor, ActorLogging, Props}
import nl.proja.pishake.operation.DS18B20Controller.{Temperature, ReadTemperature}
import nl.proja.pishake.util.{ActorDescription, ActorSupport}

import scala.language.postfixOps
import scala.sys.process._

object DS18B20Controller extends ActorDescription {

  def props(args: Any*): Props = Props[DS18B20Controller]
}

class DS18B20Controller extends Actor with ActorLogging with ActorSupport {

  val sensor = "^\\d\\d-[0-9abcdefABCDEF]+$".r
  val dS18B20FirstLine = "^.* YES$".r
  val dS18B20SecondLine = "^.*t=(-{0,1}\\d+)$".r

  def receive: Receive = {
    case ReadTemperature => readDS18B20.foreach(sender ! _)
  }

  def readDS18B20: List[Temperature] = {
    val path = "/sys/bus/w1/devices"

    Seq("ls", path).lineStream.filter(sensor.findFirstIn(_).isDefined).flatMap { sensor =>
      Seq("cat", s"$path/$sensor/w1_slave").lineStream.toList match {
        case head :: tail :: Nil => dS18B20FirstLine.findFirstIn(head).flatMap {
          _ => tail match {
            case dS18B20SecondLine(temperature) => Some(Temperature(sensor, temperature.toDouble / 1000) :: Nil)
            case _ => None
          }
        } getOrElse Nil
        case _ => Nil
      }
    } toList
  }
}
