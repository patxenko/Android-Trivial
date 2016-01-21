package es.unavarra.tlm.dscr14;


import android.content.Intent;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.IOException;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class WelcomeResponseMoveYDice extends AsyncHttpResponseHandler {
    private String pregunta,respuesta1,respuesta2,respuesta3,respuesta4;
    private ArrayList<String> a;
    private String[] resp;
    private Lienzo activity = null;
    public WelcomeResponseMoveYDice(Lienzo activity) {
        this.activity = activity;

    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeMessagePartidasCreadas message;
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeMessagePartidasCreadas.class).next();
            pregunta=message.getQuestion();
            a=message.getResponses();
            Toast.makeText(this.activity,"Vuelves a tirar", Toast.LENGTH_SHORT).show();
            Intent s=new Intent(this.activity, Lienzo.class);
            activity.startActivity(s);
            activity.finish();
        } catch (IOException e) {
            Toast.makeText(this.activity, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.activity, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }

}

