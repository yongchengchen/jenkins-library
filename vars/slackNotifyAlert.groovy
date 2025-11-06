def call(String msg, String notifyTo) {
    if (notifyTo == "all") {
        notifyTo = "<!here>"
    }

    String hook = env.SLACK_NOTIFY_ENDPOINT
    String jobName = env.JOB_NAME
    String buildNumber = env.BUILD_NUMBER
    String buildUrl = env.BUILD_URL
    String consoleUrl = "${buildUrl}console"

    msg = "Job: *${jobName}* failed. <${consoleUrl}|View Console Log>"

    sh """curl -X POST -H 'Content-type: application/json' \
        --data '{"text": "${notifyTo} ${msg}"}' \
        ${hook}
    """
}
