package es.unavarra.tlm.dscr14;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoMessageCrearPartida {
    private String token;
    public String getToken() {
        return this.token;
    }
    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    private String to;
    public String getTo() {
        return this.to;
    }
    @JsonProperty("to")
    public void setTo(String to) {
        this.to = to;
    }


    private Integer response;
    public Integer getResponse() {
        return this.response;
    }
    @JsonProperty("response")
    public void setResponse(Integer response) {
        this.response = response;
    }


}
