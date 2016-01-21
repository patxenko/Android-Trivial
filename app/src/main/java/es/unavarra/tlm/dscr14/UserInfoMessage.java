package es.unavarra.tlm.dscr14;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoMessage {
    private int profile;
    private String name;
    private String email;
    private String pass;
    private String token;



    @JsonProperty("profile")
    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getName() {
        return this.name;
    }
    public String getMail() {
        return this.email;
    }
    public String getPass() {
        return this.pass;
    }
    public String getToken() {return this.token; }


    @JsonProperty("email")
    public void setMail(String email) {
        this.email = email;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("password")
    public void setPass(String pass) {
        this.pass = pass;
    }
    @JsonProperty("token")
    public void setToken(String token) {this.token=token;}

}
