val commonsettings = Seq(
  version := "1.0.0",
  organization := "io.github.pityka",
  scalaVersion := "2.11.8")

lazy val root = crossProject.crossType(CrossType.Pure).in(file(".")).
  settings(commonsettings:_*).
  settings(
    name:="stringsplit",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.0" % "test").
  settings(reformatOnCompileSettings:_*)


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
