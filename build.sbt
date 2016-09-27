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
