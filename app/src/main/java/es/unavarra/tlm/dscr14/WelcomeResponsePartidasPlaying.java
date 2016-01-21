package es.unavarra.tlm.dscr14;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.IOException;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;
import android.widget.ArrayAdapter;


public class WelcomeResponsePartidasPlaying extends AsyncHttpResponseHandler {

    private ArrayList a;
    private String[] lista;
    private String[] listaB;
    private String editor;
    private String[] ed;
    private String fin;
    private Integer n;
    private String numerito;
    private PartidasPlaying activity = null;
    private Integer loc;
    private String l;
    public static String data;
    private String[] aj;
    private String rec;
    private String an;
    public static String tokenJuego;

    public WelcomeResponsePartidasPlaying(PartidasPlaying activity) {
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
                listaB[b] =a.get(b).toString();
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
                fin = "Juego " + numerito + " creado por: " + editor + ".\nJugadores disponibles: 0.";
                lista[b] =fin;
            }
            Integer c = a.size();
            String num = c.toString() + " partidas actualmente jugando";
            Toast.makeText(this.activity, num, Toast.LENGTH_SHORT).show();

            //MOSTRAMOS EL LISTADO
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.activity, android.R.layout.simple_list_item_1, lista);
            PartidasPlaying.listView3.setAdapter(adapter);
        }
        if(a.size()==0){
            an="Actualmente no tienes partidas pendientes";
            Toast.makeText(this.activity, an, Toast.LENGTH_SHORT).show();
            activity.finish();
        }
        //ACTIVAMOS LA OPCION DE CLICK EN CADA ELEMENTO DE LA LISTA
        PartidasPlaying.listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                loc=position;
                data=(String)parentAdapter.getItemAtPosition(position);
                juega();

            }
        });



    }

    public void juega(){
        rec=listaB[loc];
        aj=rec.split(",");
        rec=aj[0];
        aj=rec.split("=");
        rec=aj[1];
        l=loc.toString();
        Intent s=new Intent(this.activity, Lienzo.class);
        tokenJuego=rec;
        activity.startActivity(s);
        activity.finish();
    }
    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.activity, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }
}

