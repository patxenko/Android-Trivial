package es.unavarra.tlm.dscr14;

import android.util.Log;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Array;

import cz.msebera.android.httpclient.Header;


public class WelcomeResponseCrearPartida extends AsyncHttpResponseHandler {
    String respuesta="";
    Integer ava=0;
    String la="Partida creada con exito por: ";
    private Inicio activity = null;

    public WelcomeResponseCrearPartida(Inicio activity) {
        this.activity = activity;

    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeResponseMessage message = null;
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeResponseMessage.class).next();
            respuesta=message.getToken();
            ava=message.getAvailable();
            String mierda=ava.toString();
            la=la+message.nombreCreador();
            Toast.makeText(this.activity, la, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this.activity, "Cannot read response", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.activity, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }
}

