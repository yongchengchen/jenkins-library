def call(String api, String command, String notifyTo = "all", String abfolder="") {
    command = java.net.URLEncoder.encode(command, "UTF-8")
    String secret = env.WSS_SECRET
    String folderQuery = ""
    if (abfolder?.trim()) {
        folderQuery = "abfolder=${abfolder}\\&"
    }

    def cmd = "~/wscat -c ${api}?${folderQuery}command=${command}\\&secret=${secret} -e _COMMAND_DONE_ -r 1"

    def output = ""
    def excuteCode = 0
    try {
        output = sh(script: cmd, returnStdout: true).trim()
    } catch (Exception e) {
        // sh会抛异常时，可通过e.getMessage()看错误
        output = e.getMessage()
        excuteCode = 2 // 你原来用的2代表错误
    }

    if (excuteCode == 2 || output.contains("Error") || output.contains("Exception")) {
        slackNotifyAlert("Job failed with code ${excuteCode}\nOutput:\n${output}", notifyTo)
        error("Job failed: ${output}")
    }
}
