package es.unavarra.tlm.dscr14;

import android.widget.Toast;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.IOException;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class WelcomeResponseMove extends AsyncHttpResponseHandler {

    private String pregunta,respuesta1,respuesta2,respuesta3,respuesta4;
    private ArrayList<String> a;
    private String[] resp;
    private MueveResponde activity = null;
    public WelcomeResponseMove(MueveResponde activity) {
        this.activity = activity;

    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeMessagePartidasCreadas message;
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeMessagePartidasCreadas.class).next();
            pregunta=message.getQuestion();
            a=message.getResponses();
            resp=new String[a.size()];
            for (int h=0;h<a.size();h++){
                resp[h]=a.get(h).toString();
            }
            respuesta1=resp[0];
            respuesta2=resp[1];
            respuesta3=resp[2];
            respuesta4=resp[3];
        } catch (IOException e) {
            Toast.makeText(this.activity, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
        MueveResponde.tv1.setText(pregunta);
        MueveResponde.cb1.setText(respuesta1);
        MueveResponde.cb2.setText(respuesta2);
        MueveResponde.cb3.setText(respuesta3);
        MueveResponde.cb4.setText(respuesta4);
}

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.activity, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }

}

