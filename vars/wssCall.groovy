def call(String api, String command, String ipport = ""){
    command = java.net.URLEncoder.encode(command, "UTF-8")
    String secret = env.WSS_SECRET
    String resolve = ""
    if (ipport != "") {
        resolve = "--resolve ${ipport}"
    }
    excuteCode = sh(script: "~/wscat -c ${api}?command=${command}\\&secret=${secret} -e _COMMAND_DONE_ -r 1 ${resolve}", returnStatus: true)
    if (excuteCode == 2) {
        slackNotifyAlert("${excuteCode}")
        error "Job fail"
    }
}