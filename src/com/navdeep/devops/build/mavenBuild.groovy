#!groovy
package com.navdeep.devops.build

/***********************************
***** function to compile code *****
************************************/
def compile(String POM_FILE, String MVN_GOALS)
{
   try {
      MAVEN_HOME=tool name: 'MAVEN3', type: 'maven'
      JAVA_HOME=tool name: 'JDK8', type: 'jdk'
      withEnv(['JAVA_HOME=$JAVA_HOME', 'MAVEN_HOME=$MAVEN_HOME', 'PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH']) {
         sh returnStdout: true, script: "mvn -f $POM_FILE $MVN_GOALS"
      }   
   }
   catch (Exception e) {
     print "\u001B[41mERROR: mvn failed while running goals $MVN_GOALS, exiting..."
     throw e
   }
}
