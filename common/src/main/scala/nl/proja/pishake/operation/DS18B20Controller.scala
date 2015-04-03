package nl.proja.pishake.operation

import java.time.OffsetDateTime

import nl.proja.pishake.util.ActorReference


object DS18B20Controller extends ActorReference {

  trait DS18B20Operation extends Serializable

  object ReadTemperature extends DS18B20Operation

  case class Temperature(sensor: String, value: Double, timestamp: OffsetDateTime = OffsetDateTime.now()) extends DS18B20Operation

}
