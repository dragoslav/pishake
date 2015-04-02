package nl.proja.pishake.operation

import java.time.OffsetDateTime

import nl.proja.pishake.util.ActorReference


object DS18B20Controller extends ActorReference {

  trait DS18B20Operation extends Serializable

  object ReadDS18B20 extends DS18B20Operation

  case class DS18B20(name: String, temperature: Double, timestamp: OffsetDateTime = OffsetDateTime.now()) extends DS18B20Operation

}
