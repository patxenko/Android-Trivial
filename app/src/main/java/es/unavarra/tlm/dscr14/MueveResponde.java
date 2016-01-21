package es.unavarra.tlm.dscr14;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class MueveResponde extends AppCompatActivity {

    private String url,url2;
    private String enga;
    public static TextView tv1;
    public static CheckBox cb1,cb2,cb3,cb4;
    private Integer contador;
    private Integer resp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mueve_responde);
        tv1=(TextView)findViewById(R.id.tv1);
        cb1=(CheckBox)findViewById(R.id.cb1);
        cb2=(CheckBox)findViewById(R.id.cb2);
        cb3=(CheckBox)findViewById(R.id.cb3);
        cb4=(CheckBox)findViewById(R.id.cb4);

        String Token=WelcomeResponseLogin.token;
        UserInfoMessageCrearPartida message = new UserInfoMessageCrearPartida();
        message.setToken(Token);
        String output="";
        enga=Lienzo.direccion;
        message.setTo(enga);
        url="http://trivial.tatai.es/game/"+WelcomeResponsePartidasPlaying.tokenJuego+"/move.json";
        try {
            output = new ObjectMapper().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        try {

            client.post(this,url,new StringEntity(output), "application/json",new WelcomeResponseMove(this));
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
    public void responde(View view){
        contador=0;
        resp=0;
        if (cb1.isChecked()){
            contador++;
            resp=0;
        }
        if (cb2.isChecked()) {
            contador++;
            resp=1;
        }
        if (cb3.isChecked()){
            contador++;
            resp=2;
        }
        if (cb4.isChecked()) {
            contador++;
            resp=3;
        }
        if (contador>1){
            Toast.makeText(this,"Solo debe marcar una respuesta" , Toast.LENGTH_SHORT).show();
        }
        if (contador==0){
            Toast.makeText(this,"Debe marcar alguna respuesta" , Toast.LENGTH_SHORT).show();
        }
        if (contador==1){
            String Token2=WelcomeResponseLogin.token;
            UserInfoMessageCrearPartida message = new UserInfoMessageCrearPartida();
            message.setToken(Token2);
            message.setResponse(resp);
            String output2="";
            url2="http://trivial.tatai.es/game/"+WelcomeResponsePartidasPlaying.tokenJuego+"/response.json";
            try {
               output2 = new ObjectMapper().writeValueAsString(message);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            AsyncHttpClient client = new AsyncHttpClient();
            try{
              client.post(this,url2,new StringEntity(output2), "application/json",new WelcomeResponde(this));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Intent in=new Intent(this, Lienzo.class);
            startActivity(in);
            finish();

        }
    }

}
