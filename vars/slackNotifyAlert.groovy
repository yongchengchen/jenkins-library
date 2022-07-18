def call(String msg, String notifyTo) {
    if (notifyTo == "all") {
        notifyTo = "<!here>"
    }
    String hook = env.SLACK_NOTIFY_ENDPOINT
    String jobName = env.JOB_NAME
    msg = "Job:${jobName} Fail"
    sh("curl -X POST -H 'Content-type: application/json' --data '{\"text\":\"${notifyTo} ${msg}\"}' ${hook}");
}