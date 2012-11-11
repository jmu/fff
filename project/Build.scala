import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "fff"
    val appVersion      = "0.1-Beta"

    val appDependencies = Seq(
      // Add your project dependencies here,
        "mysql" % "mysql-connector-java" % "5.1.18"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here  
        resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"    
    )

}
