import org.utility.Utility
def call(Map config=[:], String buildResult){
    if (config.type == "slack") {
        echo Utility.SLACK_MESSAGE
        echo config.message
        slackNotitfy()
    } else {
        echo Utility.EMAIL_MESSAGE
        echo config.message
    }
}


def slackNotitfy(String buildResult) {
  if ( buildResult == "SUCCESS" ) {
    slackSend channel: "#eSkinDoctor-Project", color: "good", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} was successful"
  }
  else if( buildResult == "FAILURE" ) { 
    slackSend channel: "#eSkinDoctor-Project", color: "danger", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} was failed"
  }
  else if( buildResult == "UNSTABLE" ) { 
    slackSend channel: "#eSkinDoctor-Project", color: "warning", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} was unstable"
  }
  else {
    slackSend channel: "#eSkinDoctor-Project", color: "danger", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} its resulat was unclear"	
  }
}
