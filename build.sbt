val commonsettings = Seq(
  version := "1.1.1",
  organization := "io.github.pityka",
  scalaVersion := "2.13.5",
  crossScalaVersions := Seq("2.12.13")
)

lazy val root = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("."))
  .settings(commonsettings: _*)
  .settings(
    name := "stringsplit",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.5" % "test",
    publishTo := sonatypePublishToBundle.value
  )

lazy val sharedJVM = root.jvm

lazy val sharedJS = root.js

pomExtra in Global := {
  <url>https://pityka.github.io/utils-string</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>https://opensource.org/licenses/MIT</url>
    </license>
  </licenses>
  <scm>
    <connection>scm:git:github.com/pityka/utils-string</connection>
    <developerConnection>scm:git:git@github.com:pityka/utils-string</developerConnection>
    <url>github.com/pityka/utils-string</url>
  </scm>
  <developers>
    <developer>
      <id>pityka</id>
      <name>Istvan Bartha</name>
      <url>https://pityka.github.io/utils-string/</url>
    </developer>
  </developers>
}
