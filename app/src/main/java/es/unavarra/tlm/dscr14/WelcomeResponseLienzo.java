package es.unavarra.tlm.dscr14;


import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class WelcomeResponseLienzo extends AsyncHttpResponseHandler{

    private String cade1;
    private Lienzo context = null;
    private ArrayList ssss,stat1;
    private ArrayList tabl;
    private String g1;
    private String g2;
    private String g3;
    private String g4,turnode;
    private String[] m;
    private Integer ronda;
    private Integer turno;
    String at1,at2;
    private String accionUser;
    private String queso1,queso2;
    private String[] q1,q2;

    public WelcomeResponseLienzo(Lienzo context) {
        this.context = context;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeMessageJugar message;
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeMessageJugar.class).next();
            cade1 = message.nombreCreador();
            ssss = message.getPlayers();
            ronda = message.getRound();
            turno = message.getTurn();
            stat1 = message.getStatus();
            at1 = stat1.get(0).toString();
            at2 = stat1.get(1).toString();
            tabl = message.getBoard();
            String gg = tabl.toString();
            queso1=at1;
            queso2=at2;
        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
        //-----------------------------DATOS PRIMER JUGADOR;
        g1="Jugador: "+cade1; //nombre creador
        Lienzo.tv3.setText(g1);
        //STATUS (action,position,hq);
        String[] aa;  //action
        aa = new String[3];
        aa=at1.split(",");
        String b=aa[0];
        aa=b.split("=");
        String bprim=aa[1];
        String pr="accion: "+bprim;
        aa=at1.split(","); //position
        b=aa[1];
        String posicion1=b;
        aa=at1.split(",");//hq quesitos
        b=aa[2];
        //quesos
        q1=new String[4];
        q1=queso1.split("=");
        queso1=q1[3];
        queso1=queso1.replace("}","");
        queso1=queso1.replace("[","");
        queso1=queso1.replace("]", "");
        String quesitos1="Quesitos: "+queso1;
        Integer longitud=queso1.length();

        if (longitud>6){
            String[] enga;
            enga=new String[queso1.split(",").length];
            enga=queso1.split(",");
            Integer len=enga.length;
            if (len>=6){
                Toast.makeText(this.context,"Enhorabuena!! Has ganado!", Toast.LENGTH_SHORT).show();
                Lienzo.tv7.setText("Enhorabuena!! Has ganado!");
                Lienzo.bot1.setVisibility(View.INVISIBLE);
            }
        }
        String datos1=pr+", "+posicion1+", "+quesitos1;
        Lienzo.tv1.setText(datos1);
        //---------------------datos jugador 2
        g2=ssss.get(1).toString(); //nombre jugador
        m=new String[2];
        m=g2.split("=");
        g2=m[1];
        g2=g2.replace("}","");
        Lienzo.tv4.setText("Jugador: "+g2);
        aa = new String[3]; //action
        aa=at2.split(",");
        b=aa[0];
        aa=b.split("=");
        String bseg=aa[1];
        pr="accion: "+bseg;
        aa=at2.split(","); //position
        b=aa[1];
        String posicion2=b;
        aa=at2.split(",");//hq quesitos
        b=aa[2];
        //quesos
        q2=new String[4];
        q2=queso2.split("=");
        queso2=q2[3];
        queso2=queso2.replace("}","");
        queso2=queso2.replace("[","");
        queso2=queso2.replace("]","");
        String quesitos2="Quesitos: "+queso2;
        Integer longitud2=queso1.length();

        if (longitud2>6){
            String[] enga2;
            enga2=new String[queso2.split(",").length];
            enga2=queso2.split(",");
            Integer len2=enga2.length;
            if (len2>=6){
                Toast.makeText(this.context,"Lo sentimos... Has perdido", Toast.LENGTH_SHORT).show();
                Lienzo.tv7.setText("Lo sentimos... Has perdido");
                Lienzo.bot1.setVisibility(View.INVISIBLE);
            }
        }
        String datos2=pr+", "+posicion2+", "+quesitos2;
        Lienzo.tv2.setText(datos2);

        g3="Ronda: "+ronda.toString();
        //Lienzo.tv3.setText(g3);
        turnode=ssss.get(turno).toString();
        m=turnode.split("=");
        turnode=m[1];
        turnode=turnode.replace("}", "");
        g4="Es el turno de: "+turnode;
        Lienzo.tv7.setText(g4);
        String nom=WelcomeResponseLogin.nameUser;
        if (nom.equals(cade1)){
            accionUser=bprim;
        }
        if (nom.equals(g2)){
            accionUser=bseg;
        }
        if (nom.equals(turnode) && accionUser.equals("dice")){
            Lienzo.bot1.setVisibility(View.VISIBLE);
        }
        if (nom.equals(turnode) && accionUser.equals("choose")){
            Lienzo.direccion="left";
            Intent hahah=new Intent(this.context, MueveResponde.class);
            this.context.startActivity(hahah);
            this.context.finish();
        }
        if (nom.equals(turnode) && accionUser.equals("response")){
            String Token2=WelcomeResponseLogin.token;
            UserInfoMessageCrearPartida message2 = new UserInfoMessageCrearPartida();
            message2.setToken(Token2);
            Integer rep=0;
            message2.setResponse(rep);
            String output2="";
            String url2="http://trivial.tatai.es/game/"+WelcomeResponsePartidasPlaying.tokenJuego+"/response.json";
            try {
                output2 = new ObjectMapper().writeValueAsString(message2);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            AsyncHttpClient client = new AsyncHttpClient();
            try{
                client.post(this.context,url2,new StringEntity(output2), "application/json",new WelcomeResponde2(this.context));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.context, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }

}