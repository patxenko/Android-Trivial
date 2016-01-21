package es.unavarra.tlm.dscr14;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Right {
    private String type;
    public String getType() {
        return this.type;
    }
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    private String category;
    public String getCategory() {
        return this.category;
    }
    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }


    private Integer position;
    public Integer getPosition() {
        return this.position;
    }
    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
    }
}
