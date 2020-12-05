#!/usr/bin/env groovy

import org.docker.*

def call(Map projectDetails) {
        echo " ${env.WORKSPACE}"
        def image_id = projectDetails.image_id
        def dockerFile="Dockerfile_"+projectDetails.serviceName
        if(!projectDetails.containsKey("dockerFile")) {
             dockerFile =  projectDetails.dockerFile                 
        }
        sh "docker build -t ${image_id} -f ${dockerFile} ."
        withCredentials([string(credentialsId: 'DOCKER_HUB_CREDENTIALS', variable: 'DOCKER_HUB_CREDENTIALS')]) {
        new Docker().hubLogin(projectDetails.dockerRepo, DOCKER_HUB_CREDENTIALS);
        //sh "docker login -u ${projectDetails.dockerRepo} -p ${DOCKER_HUB_CREDENTIALS}"
        sh "docker tag ${image_id} ${projectDetails.dockerRepo}/${image_id}"
        sh "docker push ${projectDetails.dockerRepo}/${image_id}"
        }
}

