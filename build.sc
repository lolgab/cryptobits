import mill._, scalalib._, publish._

object cryptobits extends mill.Cross[CryptobitsModule]("3.3.3", "2.13.6", "2.12.8", "2.11.12", "2.10.7")

class CryptobitsModule(val crossScalaVersion: String) extends CrossScalaModule with PublishModule {

  def artifactName = "cryptobits"
  def publishVersion = "1.3.1"
  def ivyDeps = Agg(ivy"commons-codec:commons-codec:1.12")

  object test extends Tests {
    val scalaCheckVersion = if(crossScalaVersion.startsWith("3.")) "1.18.0" else "1.14.0"
    def ivyDeps = Agg(ivy"org.scalacheck::scalacheck:$scalaCheckVersion")
    def testFramework = "org.scalacheck.ScalaCheckFramework"
  }

  def pomSettings = PomSettings(
    organization = "org.reactormonk",
    description = "Simple crypto for signing auth tokens",
    url = "https://github.com/reactormonk/cryptobits",
    licenses = Seq(License.MIT),
    versionControl = VersionControl.github("reactormonk", "cryptobits"),
    developers = Seq(
      Developer("reactormonk", "Simon Hafner", "https://github.com/reactormonk")
    )
  )
}
