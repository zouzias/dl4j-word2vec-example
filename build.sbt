name := "dl4j-word2vec"
organization := "org.zouzias"
scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.11.8")
licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))
homepage := Some(url("https://github.com/zouzias/spark-lucenerdd"))

scalacOptions ++= Seq("-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-language:implicitConversions")

javacOptions ++= Seq("-Xlint", "-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")


libraryDependencies ++= Seq(
  "org.nd4j"  % "nd4j-native-platform" % "0.7.0",
  "org.deeplearning4j"  % "deeplearning4j-core" % "0.7.0",
  "org.deeplearning4j"  % "deeplearning4j-nlp" % "0.7.0",
  "org.scala-lang"     % "scala-library" % scalaVersion.value % "compile"
)