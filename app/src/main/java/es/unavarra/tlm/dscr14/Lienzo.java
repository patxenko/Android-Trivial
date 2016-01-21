package es.unavarra.tlm.dscr14;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import java.io.UnsupportedEncodingException;
import cz.msebera.android.httpclient.entity.StringEntity;

public class Lienzo extends AppCompatActivity {

    private String url,url2;
    public static TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv10,tv11;
    public static Button botA,botB,bot1;
    public static String direccion;
    public static String dice1,dice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lienzo);
        dice1=WelcomeResponseDice.opt1;
        dice2=WelcomeResponseDice.opt2;
        tv1 =(TextView)findViewById(R.id.tv1);
        tv2 =(TextView)findViewById(R.id.tv2);
        tv3 =(TextView)findViewById(R.id.tv3);
        tv4 =(TextView)findViewById(R.id.tv4);
        tv7 =(TextView)findViewById(R.id.tv7);
        tv10 =(TextView)findViewById(R.id.tv10);
        tv11 =(TextView)findViewById(R.id.tv11);
        botA=(Button)findViewById(R.id.botA);
        botB=(Button)findViewById(R.id.botB);
        bot1=(Button)findViewById(R.id.bot1);
        dice1="";
        dice2="";

        String Token=WelcomeResponseLogin.token;
        UserInfoMessageCrearPartida message = new UserInfoMessageCrearPartida();
        message.setToken(Token);
        String output="";
        url="http://trivial.tatai.es/game/"+WelcomeResponsePartidasPlaying.tokenJuego+"/info.json";
        try {
            output = new ObjectMapper().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        try {

            client.post(this,url,new StringEntity(output), "application/json",new WelcomeResponseLienzo(this));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void lanzaDado(View view) throws JsonProcessingException {
        bot1.setVisibility(View.GONE);
        botA.setVisibility(View.VISIBLE);
        botB.setVisibility(View.VISIBLE);
        //convertimos lo ingresado en una cadena JSON
        UserInfoMessageCrearPartida message2 = new UserInfoMessageCrearPartida();
        String Token2=WelcomeResponseLogin.token;
        message2.setToken(Token2);
        String output2 = new ObjectMapper().writeValueAsString(message2);
        url2="http://trivial.tatai.es/game/"+WelcomeResponsePartidasPlaying.tokenJuego+"/dice.json";
        AsyncHttpClient client2 = new AsyncHttpClient();
        try {
            client2.post(this, url2, new StringEntity(output2), "application/json", new WelcomeResponseDice(this));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void mueveLeft(View view) throws JsonProcessingException{
        if (dice1.equals("dice")){
            //MOVEMOS CASILLA Y VOLVEMOS A TIRAR
            dice1="";
            String Token=WelcomeResponseLogin.token;
            UserInfoMessageCrearPartida message = new UserInfoMessageCrearPartida();
            message.setToken(Token);
            String output="";
            direccion="left";
            message.setTo(direccion);
            url="http://trivial.tatai.es/game/"+WelcomeResponsePartidasPlaying.tokenJuego+"/move.json";
            try {
                output = new ObjectMapper().writeValueAsString(message);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            AsyncHttpClient client = new AsyncHttpClient();
            try {

                client.post(this,url,new StringEntity(output), "application/json",new WelcomeResponseMoveYDice(this));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        else{
            direccion="left";
            Intent hahah=new Intent(this, MueveResponde.class);
            startActivity(hahah);
            finish();
        }

    }
    public void mueveRight(View view) throws  JsonProcessingException{
        if (dice2.equals("dice")){
            //MOVEMOS CASILLA Y VOLVEMOS A TIRAR
            dice2="";
            String Token=WelcomeResponseLogin.token;
            UserInfoMessageCrearPartida message = new UserInfoMessageCrearPartida();
            message.setToken(Token);
            String output="";
            direccion="right";
            message.setTo(direccion);
            url="http://trivial.tatai.es/game/"+WelcomeResponsePartidasPlaying.tokenJuego+"/move.json";
            try {
                output = new ObjectMapper().writeValueAsString(message);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            AsyncHttpClient client = new AsyncHttpClient();
            try {

                client.post(this,url,new StringEntity(output), "application/json",new WelcomeResponseMoveYDice(this));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        else{
            direccion="right";
            Intent ina=new Intent(this, MueveResponde.class);
            startActivity(ina);
            finish();
        }
    }
    public void volver(View view){
        finish();
    }

}
