package es.unavarra.tlm.dscr14;

import android.view.View;
import android.widget.AdapterView;
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
import android.widget.ArrayAdapter;


public class WelcomeResponsePartidasAvailables extends AsyncHttpResponseHandler {
    private ArrayList a;
    private String[] lista;
    private String[] listaB;
    private String editor;
    private String[] ed;
    private String fin;
    private Integer n;
    private String numerito;
    private PartidasAvailables activity = null;
    private String gnum;
    private Integer loc;
    private String data;
    private String rec;
    private String[] aj;
    private String l;
    private String tokenJuego;
    private String url;

    public WelcomeResponsePartidasAvailables(PartidasAvailables activity) {
        this.activity = activity;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeMessagePartidasCreadas message;
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeMessagePartidasCreadas.class).next();
            a=message.getGames();

        } catch (IOException e) {
            Toast.makeText(this.activity, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
        if (a.size()>0) {
            lista = new String[a.size()];
            listaB = new String[a.size()];
            for (int b = 0; b < a.size(); b++) {
                //EN LISTAB ALMACENAMOS LOS VALORES COMPLETOS COMO STRING DE CADA JUEGO
                listaB[b] = a.get(b).toString();
                editor = a.get(b).toString();
                ed = new String[5];
                ed = editor.split("=");
                editor = ed[3];
                ed = editor.split(",");
                editor = ed[0];
                editor = editor.replace("}", "");
                //editor=editor.replace("}","");
                n = b + 1;
                numerito = n.toString();
                fin = "Juego " + numerito + " creado por:" + editor + ".\nJugadores disponibles: 1.";
                lista[b] = fin;

            }
            Integer c = a.size();
            String num = c.toString() + " juegos disponibles para jugar";
            Toast.makeText(this.activity, num, Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.activity, android.R.layout.simple_list_item_1, lista);
            PartidasAvailables.listView2.setAdapter(adapter);
        }
        if (a.size()==0){
            gnum="No existe ninguna partida disponible hasta el momento";
            Toast.makeText(this.activity, gnum, Toast.LENGTH_SHORT).show();
        }

        //ACTIVAMOS LA OPCION DE CLICK EN CADA ELEMENTO DE LA LISTA
        PartidasAvailables.listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                loc=position;
                data=(String)parentAdapter.getItemAtPosition(position);
                saca();

            }
        });

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.activity, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }
    public void saca(){
        rec=listaB[loc];
        aj=rec.split(",");
        rec=aj[0];
        aj=rec.split("=");
        rec=aj[1];
        l=loc.toString();
        tokenJuego=rec;
        String Token=WelcomeResponseLogin.token;
        UserInfoMessageCrearPartida message = new UserInfoMessageCrearPartida();
        message.setToken(Token);
        String output="";
        url="http://trivial.tatai.es/game/"+tokenJuego+"/join.json";
        try {
            output = new ObjectMapper().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        try {

            client.post(this.activity,url,new StringEntity(output), "application/json",new WelcomeResponseJoin(this.activity));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

