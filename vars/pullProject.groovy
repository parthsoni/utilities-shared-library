#!/usr/bin/env groovy

def call(Map projectDetails) {
        projectDetails.each{ k, v -> println "${k}:${v}" }
        echo " ${projectDetails.image_id}"
        def image_id = ${projectDetails.image_id}
        sh "cd ${env.WORKSPACE}/eskinfront && npm install && npm run build"
        sh "mkdir -p ${env.WORKSPACE}${list.dest} && cp -r ${list.src} ${list.dest}"
        sh "docker build -t ${image_id} -f Dockerfile_nginx ."
        withCredentials([string(credentialsId: 'DOCKER_HUB_CREDENTIALS', variable: 'DOCKER_HUB_CREDENTIALS')]) {
        sh "docker login -u parthsoni -p ${DOCKER_HUB_CREDENTIALS}"
        sh "docker tag ${image_id} parthsoni/${image_id}"
        sh "docker push parthsoni/${image_id}"
        }
}

