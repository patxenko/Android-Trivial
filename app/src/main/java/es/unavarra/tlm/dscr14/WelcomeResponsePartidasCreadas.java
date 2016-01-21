package es.unavarra.tlm.dscr14;


import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.IOException;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class WelcomeResponsePartidasCreadas extends AsyncHttpResponseHandler {

    private ArrayList a=new ArrayList();
    private String[] lista;
    private PartidasCreadas activity = null;
    private String editor;
    private String[] ed;
    private String fin;
    private Integer n;
    private String numerito;
    private String response;

    public WelcomeResponsePartidasCreadas(PartidasCreadas activity) {
        this.activity = activity;

    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeMessagePartidasCreadas message = null;
        try {
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeMessagePartidasCreadas.class).next();
            a=message.getGames();
            //String json = respuesta;
            //UserInfoMessage message1 = (UserInfoMessage)new ObjectMapper().readValues(new JsonFactory().createParser(json), UserInfoMessage.class).next();
            //String token1=message1.getToken();
           // Toast.makeText(this.activity, respuesta, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this.activity, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
        //respuesta=a.get(0).toString();
        if (a.size()>0) {
            lista = new String[a.size()];
            for (int b = 0; b < a.size(); b++) {
                //RECOGEMOS LOS DATOS IMPORTANTES
                editor = a.get(b).toString();
                ed = new String[5];
                ed = editor.split("=");
                editor = ed[4];
                editor = editor.replace("}", "");
                n = b + 1;
                numerito = n.toString();
                fin = "Juego numero " + numerito + ".\nJugadores disponibles: " + editor;
                lista[b] = new String(fin);
            }
            Integer c = a.size();
            String num = c.toString() + " juegos creados por ti";
            Toast.makeText(this.activity, num, Toast.LENGTH_SHORT).show();


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.activity, android.R.layout.simple_list_item_1, lista);
            PartidasCreadas.listView.setAdapter(adapter);
        }
        if (a.size()==0){
            response="No hay partidas creadas por ti";
            Toast.makeText(this.activity, response, Toast.LENGTH_SHORT).show();
            activity.finish();
        }

    }
    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.activity, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }
}
