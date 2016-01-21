package es.unavarra.tlm.dscr14;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public class WelcomeResponseMessage {
    //para el mensaje de bienvenida
    private String message;
    public String getMessage() {
        return this.message;
    }
    @JsonProperty("message")
    public void setMessage(String message) {
       this.message = message;
    }

    //para el token del registro y login
    private String token;
    public String getToken() {
        return this.token;
    }
    @JsonProperty("token")
    public void setToken(String token) {
       this.token = token;
    }

    //para el nombre del login
    private String name;
    public String getNombre() {
        return this.name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    //para crear juego
    private Integer available;
    public Integer getAvailable() {
        return this.available;
    }
    @JsonProperty("available")
    public void setAvailable(Integer available) {
        this.available = available;
    }


    //
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

}
