package es.unavarra.tlm.dscr14;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import java.io.UnsupportedEncodingException;
import cz.msebera.android.httpclient.entity.StringEntity;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
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

    public void crearPartida(View view) throws JsonProcessingException {
        //convertimos lo ingresado en una cadena JSON
        UserInfoMessageCrearPartida message = new UserInfoMessageCrearPartida();
        String Token=WelcomeResponseLogin.token;
        message.setToken(Token);
        String output = new ObjectMapper().writeValueAsString(message);
        //Toast notificacion = Toast.makeText(this,output, Toast.LENGTH_LONG);
        //notificacion.show();
        AsyncHttpClient client = new AsyncHttpClient();
        try {

            client.post(this, "http://trivial.tatai.es/game/create.json", new StringEntity(output), "application/json", new WelcomeResponseCrearPartida(this));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void partidasCreadas(View view) throws JsonProcessingException{
        Intent b=new Intent(this, PartidasCreadas.class);
        startActivity(b);
    }
    public void partidasAvailables(View view) throws JsonProcessingException{
        Intent x=new Intent(this, PartidasAvailables.class);
        startActivity(x);
    }
    public void playing(View view) throws JsonProcessingException{
        Intent r=new Intent(this, PartidasPlaying.class);
        startActivity(r);
    }
    public void cierraSesion(View view){
        finish();
    }

}
