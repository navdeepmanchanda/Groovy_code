#!groovy
package com.navdeep.devops.scm

/*************************************
***** function to clone git code *****
**************************************/
def checkout(String GIT_URL, String BRANCH, String GIT_CREDENTIALS)
{
   try {
      wrap([$class: 'AnsiColorBuildWrapper']) {
        if ( "${env.BRANCH_NAME}" == "null" ) {
           BRANCH = "${env.BRANCH_NAME}"
        }
        println "\u001B[32mINFO: cloning code from git repository $GIT_URL and branch $BRANCH, please wait..."
        checkout([$class: 'GitSCM', branches: [[name: "*/$BRANCH"]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "$GIT_CREDENTIALS", url: "$GIT_URL"]]])
      }
   }
   catch (Exception e)
   {
      error "\u001B[41mERROR: Git clone failed for repository $GIT_URL, exiting..."
   }
}
