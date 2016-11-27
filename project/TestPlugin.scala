import sbt._
import sbt.Keys._

import sbtassembly.AssemblyPlugin.autoImport.assembly

object TestPlugin extends AutoPlugin {
  object autoImport {
    val addAssemblyArtifact = settingKey[Boolean]("When set, add the assembly JAR to published artifacts")
  }

  import autoImport._

  override def trigger: PluginTrigger = allRequirements

  override def projectSettings: Seq[_root_.sbt.Def.Setting[_]] = {
    Seq(
      scalaVersion := "2.10.6",
      addAssemblyArtifact := false,
      publishM2 := Def.taskDyn({
        if (addAssemblyArtifact.value) {
          println("adding assembly artifact!")
          addArtifact(artifact in (Compile, assembly), assembly)
        } else {
          println("not adding assembly artifact")
        }
        publishM2
      }).value
    )
  }
}
