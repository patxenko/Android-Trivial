package es.unavarra.tlm.dscr14;


import android.widget.Toast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;
import cz.msebera.android.httpclient.Header;

public class WelcomeResponseHandler extends AsyncHttpResponseHandler{

    private MainActivity context = null;


    public WelcomeResponseHandler(MainActivity context) {
        this.context = context;

    }


    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeResponseMessage message;
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeResponseMessage.class).next();

            Toast.makeText(this.context,message.getMessage(),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.context, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }

}
