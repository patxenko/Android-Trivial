package es.unavarra.tlm.dscr14;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WelcomeMessagePartidasCreadas {
    private ArrayList games;
    public ArrayList getGames() {
        return this.games;
    }
    @JsonProperty("games")
    public void setGames(ArrayList games) {
        this.games = games;
    }

    private String question;
    public String getQuestion() {
        return this.question;
    }
    @JsonProperty("question")
    public void setQuestion(String question) {
        this.question = question;
    }


    private ArrayList responses;
    public ArrayList getResponses() {
        return this.responses;
    }
    @JsonProperty("responses")
    public void setResponses(ArrayList responses) {
        this.responses = responses;
    }



    private boolean correct;
    public boolean getCorrect() {
        return this.correct;
    }
    @JsonProperty("correct")
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }


    private boolean hq;
    public boolean getHq() {
        return this.hq;
    }
    @JsonProperty("hq")
    public void setHq(boolean hq) {
        this.hq = hq;
    }



    private String category;
    public String getCategory() {
        return this.category;
    }
    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

}
