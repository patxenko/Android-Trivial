package es.unavarra.tlm.dscr14;


import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;
import cz.msebera.android.httpclient.Header;

public class WelcomeResponseJoin extends AsyncHttpResponseHandler{

    private String cade;
    private PartidasAvailables context = null;
    private String men;


    public WelcomeResponseJoin(PartidasAvailables context) {
        this.context = context;

    }


    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeMessageJoin message;
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeMessageJoin.class).next();
            cade=message.getRound().toString();
            men="Te acabas de unir a una nueva partida";
            Toast.makeText(this.context,men, Toast.LENGTH_SHORT).show();
            sal();

        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.context, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }

    public void sal(){
        this.context.finish();
        Intent a=new Intent(this.context,PartidasAvailables.class);
        this.context.startActivity(a);

    }

}