package es.unavarra.tlm.dscr14;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WelcomeMessageJoin {
    private String token;
    public String getToken() {
        return this.token;
    }
    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    private Integer round;
    public Integer getRound() {
            return this.round;
        }
    @JsonProperty("round")
    public void setRound(Integer round) {
        this.round = round;
    }

    private Integer turn;
    public Integer getTurn() {
        return this.turn;
    }
    @JsonProperty("turn")
    public void setTurn(Integer turn) {
        this.turn = turn;
    }

}
