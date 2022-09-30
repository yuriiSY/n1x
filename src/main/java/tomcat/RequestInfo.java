package tomcat;

public class RequestInfo {
    private String ip;
    private String userAgent;
    private String time;

    public RequestInfo(String ip, String userAgent, String time) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
