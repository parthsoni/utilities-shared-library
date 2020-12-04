import org.utility.Utility
def call(Map config=[:]) {
    if (config.type == "slack") {
        echo Utility.SLACK_MESSAGE
        echo config.message
    } else {
        echo Utility.EMAIL_MESSAGE
        echo config.message
    }
}

