inThisBuild(
  List(
    organization := "io.github.pityka",
    homepage := Some(url("https://pityka.github.io/utils-string/")),
    licenses := List(("MIT", url("https://opensource.org/licenses/MIT"))),
    developers := List(
      Developer(
        "pityka",
        "Istvan Bartha",
        "bartha.pityu@gmail.com",
        url("https://github.com/pityka/utils-string")
      )
    )
  )
)

val commonsettings = Seq(
  scalaVersion := "2.13.6",
  crossScalaVersions := Seq("2.12.17", "2.13.6"),
  mimaPreviousArtifacts := Set(organization.value %% moduleName.value % "1.1.3")
)

lazy val root = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("."))
  .settings(commonsettings: _*)
  .settings(
    name := "stringsplit",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.10" % "test"
  )

lazy val sharedJVM = root.jvm

lazy val sharedJS = root.js
