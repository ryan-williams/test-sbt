name := "test-sbt"
version := "1.0.0-SNAPSHOT"
libraryDependencies ++= Seq(
  "com.google.guava" % "guava" % "16.0.1",
  "com.github.samtools" % "htsjdk" % "2.6.1"
)

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyShadeRules in assembly ++= Seq(
  ShadeRule.rename(
    "com.google.common.**" -> "org.hammerlab.guava.@1"
  ).inAll
)

artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.copy(`classifier` = Some("assembly"))
}

addAssemblyArtifact := true

//addArtifact(artifact in (Compile, assembly), assembly)

assemblyExcludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter { path => {
    println(s"jar: $path")
    path.data.getName != "guava-16.0.1.jar"
  }}
}
