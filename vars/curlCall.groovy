def call(String api, String command, String notifyTo = "all"){
    command = java.net.URLEncoder.encode(command, "UTF-8")
    String secret = env.WSS_SECRET
    
    String response = sh(script: "curl -s ${api}?command=${command}\\&secret=${secret}", returnStatus: true)
    echo response
}