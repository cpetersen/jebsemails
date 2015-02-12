import sbtassembly.Plugin._ 
import AssemblyKeys._

assemblySettings

mainClass in assembly := Some("io.petersen.MainApplication")
