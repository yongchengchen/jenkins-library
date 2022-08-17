def call(String api, String command, String notifyTo = "all", String abfolder=""){
    command = java.net.URLEncoder.encode(command, "UTF-8")
    String secret = env.WSS_SECRET
    String folderQuery = "";
    if (abfolder?.trim()) {
        folderQuery="abfolder=${abfolder}\\&";
    }
    
    excuteCode = sh(script: "~/wscat -c ${api}?${folderQuery}command=${command}\\&secret=${secret} -e _COMMAND_DONE_ -r 1", returnStatus: true)
    if (excuteCode == 2) {
        slackNotifyAlert("${excuteCode}", notifyTo)
        error "Job fail"
    }
}