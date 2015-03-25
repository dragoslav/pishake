package nl.proja.pishake


case class SystemInfo(hardwareInfo: HardwareInfo, memoryInfo: MemoryInfo, operatingSystemInfo: OperatingSystemInfo, javaEnvironmentInfo: JavaEnvironmentInfo, networkInfo: NetworkInfo, codecInfo: CodecInfo, clockFrequencyInfo: ClockFrequencyInfo)


case class HardwareInfo(serial: String, cpuRevision: String, cpuArchitecture: String, cpuPart: String, cpuTemperature: Float, cpuVoltage: Float, modelName: String, processor: String, revision: String, hardFloatAbi: Boolean, boardType: String)

case class MemoryInfo(total: Long, used: Long, free: Long, shared: Long, buffers: Long, cached: Long, voltageSDRamC: Float, voltageSDRamI: Float, voltageSDRamP: Float)

case class OperatingSystemInfo(name: String, version: String, arch: String, firmwareBuild: String, firmwareDate: String)

case class JavaEnvironmentInfo(vendor: String, vendorUrl: String, version: String, virtualMachine: String, runtime: String)

case class NetworkInfo(hostname: String, ipAddresses: List[String], fqdns: List[String], nameservers: List[String])

case class CodecInfo(h264: Boolean, mpg2: Boolean, wvc1: Boolean)

case class ClockFrequencyInfo(arm: Long, core: Long, h264: Long, isp: Long, v3d: Long, uart: Long, pwm: Long, emmc: Long, pixel: Long, vec: Long, hdmi: Long, dpi: Long)


