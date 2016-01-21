package es.unavarra.tlm.dscr14;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WelcomeMessageJugar {
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



    private ArrayList players;
    public ArrayList getPlayers() {
        return this.players;
    }
    @JsonProperty("players")
    public void setPlayers(ArrayList players) {
        this.players = players;
    }

    private ArrayList board;
    public ArrayList getBoard() {
        return this.board;
    }
    @JsonProperty("board")
    public void setBoard(ArrayList board) {
        this.board = board;
    }

    private ArrayList status;
    public ArrayList getStatus() {
        return this.status;
    }
    @JsonProperty("status")
    public void setStatus(ArrayList status) {
        this.status = status;
    }

    @JsonProperty("creator")
    Creator crea=new Creator();
    public String nombreCreador(){return crea.Name;}
    public class Creator{
        private String Name;
        public Creator(){

        }
        public Creator(String name){
            this.Name=name;
        }
        @JsonProperty("name")
        public void setName(String name) {

            this.Name = name;
        }
        public String getName(){
            return this.Name;
        }
    }


    private Integer shift;
    public Integer getShift() {
        return this.shift;
    }
    @JsonProperty("shift")
    public void setShift(Integer shift) {
        this.shift = shift;
    }

    private Left left;
    public Left getLeft() {
        return left;
    }

    private Right right;
    public Right getRight() {
        return right;
    }
}
