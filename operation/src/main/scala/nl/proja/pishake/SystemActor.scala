package nl.proja.pishake

import akka.actor._
import com.pi4j.system.{NetworkInfo, SystemInfo}

import scala.language.postfixOps

object SystemActor extends ActorDescription {

  def props(args: Any*): Props = Props[SystemActor]

  object Info

}

class SystemActor extends Actor with ActorLogging with ActorSupport {

  import nl.proja.pishake.SystemActor._

  def tags = "escalation" :: Nil

  def receive: Receive = {
    case Info =>
      sender ! SystemInfo(HardwareInfo(
        SystemInfo.getSerial, SystemInfo.getCpuRevision, SystemInfo.getCpuArchitecture, SystemInfo.getCpuPart, SystemInfo.getCpuTemperature, SystemInfo.getCpuVoltage, SystemInfo.getModelName, SystemInfo.getProcessor, SystemInfo.getRevision, SystemInfo.isHardFloatAbi, SystemInfo.getBoardType.name),
        MemoryInfo(SystemInfo.getMemoryTotal, SystemInfo.getMemoryUsed, SystemInfo.getMemoryFree, SystemInfo.getMemoryShared, SystemInfo.getMemoryBuffers, SystemInfo.getMemoryCached, SystemInfo.getMemoryVoltageSDRam_C, SystemInfo.getMemoryVoltageSDRam_I, SystemInfo.getMemoryVoltageSDRam_P),
        OperatingSystemInfo(SystemInfo.getOsName, SystemInfo.getOsVersion, SystemInfo.getOsArch, SystemInfo.getOsFirmwareBuild, SystemInfo.getOsFirmwareDate),
        JavaEnvironmentInfo(SystemInfo.getJavaVendor, SystemInfo.getJavaVendorUrl, SystemInfo.getJavaVersion, SystemInfo.getJavaVirtualMachine, SystemInfo.getJavaRuntime),
        NetworkInfo(NetworkInfo.getHostname, NetworkInfo.getIPAddresses.toList, NetworkInfo.getFQDNs.toList, NetworkInfo.getNameservers.toList),
        CodecInfo(SystemInfo.getCodecH264Enabled, SystemInfo.getCodecMPG2Enabled, SystemInfo.getCodecWVC1Enabled),
        ClockFrequencyInfo(SystemInfo.getClockFrequencyArm, SystemInfo.getClockFrequencyCore, SystemInfo.getClockFrequencyH264, SystemInfo.getClockFrequencyISP, SystemInfo.getClockFrequencyV3D, SystemInfo.getClockFrequencyUART, SystemInfo.getClockFrequencyPWM, SystemInfo.getClockFrequencyEMMC, SystemInfo.getClockFrequencyPixel, SystemInfo.getClockFrequencyVEC, SystemInfo.getClockFrequencyHDMI, SystemInfo.getClockFrequencyDPI)
      )
  }
}

