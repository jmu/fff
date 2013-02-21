import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "fff"
    val appVersion      = "0.1-Beta"

    val appDependencies = Seq(
      javaCore,
      javaJdbc,
      javaEbean,
      jdbc,
      // Add your project dependencies here,
      "mysql" % "mysql-connector-java" % "5.1.18"
      //"postgresql" % "postgresql" % "9.1-901-1.jdbc4"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here  
        resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"    
    )

}
