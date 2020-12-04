#!/usr/bin/env groovy

import org.docker.*

def call(Map projectDetails) {
        echo " ${env.WORKSPACE}"
        def image_id = projectDetails.image_id
        sh "cd ${env.WORKSPACE}/eskinfront && npm install && npm run build"
        sh "mkdir -p ${env.WORKSPACE}${projectDetails.dest} && cp -r ${projectDetails.src} ${projectDetails.dest}"
        sh "docker build -t ${image_id} -f Dockerfile_${projectDetails.serviceName} ."
        withCredentials([string(credentialsId: 'DOCKER_HUB_CREDENTIALS', variable: 'DOCKER_HUB_CREDENTIALS')]) {
        //hubLogin(projectDetails.dockerRepo, ${DOCKER_HUB_CREDENTIALS});
        }
        //sh "docker login -u parthsoni -p ${DOCKER_HUB_CREDENTIALS}"
 //       sh "docker tag ${image_id} ${projectDetails.dockerRepo}/${image_id}"
   //     sh "docker push ${projectDetails.dockerRepo}/${image_id}"
        
}

