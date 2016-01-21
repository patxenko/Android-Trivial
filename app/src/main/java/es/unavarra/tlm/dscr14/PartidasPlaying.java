package es.unavarra.tlm.dscr14;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class PartidasPlaying extends AppCompatActivity {

    public static ListView listView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidas_playing);

        listView3=(ListView)findViewById(R.id.listView3);

        String Token=WelcomeResponseLogin.token;
        UserInfoMessageCrearPartida message = new UserInfoMessageCrearPartida();
        message.setToken(Token);
        String output="";
        try {
            output = new ObjectMapper().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        try {

            client.post(this, "http://trivial.tatai.es/list/playing.json", new StringEntity(output), "application/json",new WelcomeResponsePartidasPlaying(this));
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
    public void inicio(View view){
        finish();
    }

}
