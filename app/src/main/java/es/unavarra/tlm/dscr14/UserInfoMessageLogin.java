package es.unavarra.tlm.dscr14;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoMessageLogin {
    private String email;
    private String pass;
    public String getMail() {
        return this.email;
    }
    public String getPass() {
        return this.pass;
    }
    @JsonProperty("email")
    public void setMail(String email) {
        this.email = email;
    }


    @JsonProperty("password")
    public void setPass(String pass) {
        this.pass = pass;
    }
}
