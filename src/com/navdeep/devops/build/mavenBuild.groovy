#!groovy
package com.navdeep.devops.build

/***********************************
***** function to compile code *****
************************************/
def compile(String POM_FILE, String MVN_GOALS)
{
   try {
      sh "mvn -f $POM_FILE $MVN_GOALS"
   }
   catch (Exception e) {
     print "\u001B[41mERROR: mvn failed while running goals $MVN_GOALS, exiting..."
     throw e
   }
}
