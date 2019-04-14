#!groovy

import com.navdeep.devops.scm.*
import com.navdeep.devops.build.*
   
def call(body)
{
   def config = [:]
   body.resolveStrategy = Closure.DELEGATE_FIRST
   body.delegate = config
   body()
   timestamps {
      currentBuild.result = "success"
      try {
        stage("\u2776 Code Checkout") {
          def g = new git()
          g.checkout("${config.GIT_URL}","${config.BRANCH}","${config.GIT_CREDENTIALS}")
        }
        stage("\u2777 Code Compile") {
          def b = new maven()
          b.compile("${config.POM_FILE}","${config.MVN_GOALS}")
        }
      }
      catch (Exception e) {
        wrap([$class: "AnsiColorBuildWrapper"]) {
          print "\u001B[41mERROR: failed to run maven pipeline, exiting..."
          currentBuild.result = "failure"
          throw e
        }
      }
      finally {
      }
   }
}
