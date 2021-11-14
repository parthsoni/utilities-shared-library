#!/usr/bin/env groovy

def call(Map projectDetails) {
    
    if(projectDetails.projectSourcePath && projectDetails.projectSourcePath !="*" ) {
            sh "cd ${projectDetails.projectSourcePath} && npm install -g npm-install-peers && npm run build" 

    }else {
            sh "sudo chmod 777 -R /usr/local/lib/node_modules"
            sh "npm install -g npm-install-peers && npm run build" 
    }
    sh "mkdir -p ${env.WORKSPACE}/${projectDetails.buildDestinationPath} && cp -r ${projectDetails.buildSourcePath} ${projectDetails.buildDestinationPath}"
}
