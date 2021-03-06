package es.unavarra.tlm.dscr14;

import android.widget.Toast;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.IOException;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;


public class WelcomeResponde extends AsyncHttpResponseHandler {

    private boolean core,hqq;
    private String categoria;
    private ArrayList<String> a;
    private String[] resp;
    private MueveResponde activity = null;
    public WelcomeResponde(MueveResponde activity) {
        this.activity = activity;

    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeMessagePartidasCreadas message;
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeMessagePartidasCreadas.class).next();
            core=message.getCorrect();
            hqq=message.getHq();
            categoria=message.getCategory();
            if (core==true) {
                Toast.makeText(this.activity, "Has respondido correctamente", Toast.LENGTH_SHORT).show();
            }
            if (core==false){
                Toast.makeText(this.activity, "Has fallado", Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            Toast.makeText(this.activity, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.activity, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }

}

