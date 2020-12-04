def call(Map list) {
    
    println "list".dump()
    
    println list.dump()

    node {
        
        stage('Clean workspace and prepare') { // for display purposes
        // Get some code from a GitHub repository
        git branch: list.branch, credentialsId: 'eSkinDoctor_Repo', url: 'https://github.com/pNAIA/eskindoctor'
    }
        stage('Build artifacts and docker image') {
        def USER_HOME = "/home/ubuntu/"
        def NGINX_BUILD_PATH = "${env.WORKSPACE}"
        def image_id = list.image_id
        //sh "cp -r ${USER_HOME}docker_config ${env.WORKSPACE}"
        sh "cd ${env.WORKSPACE}/eskinfront && npm install && npm run build"
        sh "mkdir -p ${env.WORKSPACE}/eskinfront/patient/ && cp -r list.src list.dest"
        
        dir("${env.WORKSPACE}") {
        sh "pwd"
        sh "whoami"
            sh "docker build -t ${image_id} -f Dockerfile_nginx ."
        withCredentials([string(credentialsId: 'DOCKER_HUB_CREDENTIALS', variable: 'DOCKER_HUB_CREDENTIALS')]) {
    // some block
            sh "docker login -u parthsoni -p ${DOCKER_HUB_CREDENTIALS}"
        }
        sh "docker tag ${image_id} parthsoni/${image_id}"
        sh "docker push parthsoni/${image_id}"
        }
        
        //sh "cp -r ${USER_HOME}docker_config ${env.WORKSPACE}"
        //sh "mkdir -p ${env.WORKSPACE}/eskinfront/patient/ && cp -r eskinfront/dist/* ${env.WORKSPACE}/eskinfront/patient/"
        //&& npm install && npm run build'
        
        
    }

        
    }
}
