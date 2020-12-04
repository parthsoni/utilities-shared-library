#!/usr/bin/env groovy

def call(Map projectDetails) {
    
    sh "cd ${env.WORKSPACE}/${projectDetails.projectSourcePath} && npm install && npm run build" 
    sh "mkdir -p ${env.WORKSPACE}${projectDetails.buildDestinationPath} && cp -r ${projectDetails.buildSourcePath} ${projectDetails.buildDestinationPath}"
}
