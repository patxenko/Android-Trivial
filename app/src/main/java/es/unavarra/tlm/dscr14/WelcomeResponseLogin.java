package es.unavarra.tlm.dscr14;

import android.content.Intent;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class WelcomeResponseLogin extends AsyncHttpResponseHandler {
    private Login activity = null;
    public static String token,nameUser;

    public WelcomeResponseLogin(Login activity) {
        this.activity = activity;

    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeResponseMessage message;
        String respuesta="";
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeResponseMessage.class).next();
            respuesta=message.getNombre();
            String mensajeB="Bienvenido "+respuesta;
            //respuesta=respuesta+message.getToken();
            token=message.getToken();
            nameUser=message.getNombre();
            Toast.makeText(this.activity,mensajeB, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this.activity, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
        if (!respuesta.equals("")){
            Intent a=new Intent(this.activity, Inicio.class);
            this.activity.startActivity(a);
            activity.finish();
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.activity, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }
}

