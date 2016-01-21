package es.unavarra.tlm.dscr14;

import android.view.View;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.IOException;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class WelcomeResponseDice extends AsyncHttpResponseHandler{


    private Lienzo context = null;
    private Integer shift;
    private String g4;
    private String type1,type2,cat1,cat2;
    private ArrayList b;
    private String m;
    private Integer pos1,pos2;
    private String poss1,poss2;
    private String num,resultado1,resultado2;
    public static String opt1,opt2="";


    public WelcomeResponseDice(Lienzo context) {
        this.context = context;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        WelcomeMessageJugar message = null;
        try {
            opt1="";
            opt2="";
            message = new ObjectMapper().readValues(new JsonFactory().createParser(responseBody), WelcomeMessageJugar.class).next();
            shift=message.getShift();
            g4=shift.toString();
            num="Has sacado un: "+g4;
            Toast.makeText(this.context,num, Toast.LENGTH_SHORT).show();
            type1=message.getLeft().getType();
            pos1=message.getLeft().getPosition();
            poss1=pos1.toString();
            cat1=message.getLeft().getCategory();
            if (type1.equals("dice")) {
                Lienzo.dice1="dice";
                resultado1="Primera opcion: "+poss1+", "+type1;
            }
            else{
                resultado1="Primera opcion: "+poss1+", "+type1+", "+cat1;
            }

            Lienzo.tv10.setText(resultado1);
            type2=message.getRight().getType();
            pos2=message.getRight().getPosition();
            poss2=pos2.toString();
            cat2=message.getRight().getCategory();
            if (type2.equals("dice")) {
                Lienzo.dice2="dice";
                resultado2="Segunda opcion: "+poss2+", "+type2;
            }
            else{
                resultado2="Segunda opcion: "+poss2+", "+type2+", "+cat2;
            }
            Lienzo.tv11.setText(resultado2);
            Lienzo.botA.setVisibility(View.VISIBLE);
            Lienzo.botB.setVisibility(View.VISIBLE);

        } catch (IOException e) {
            Toast.makeText(this.context, "Cannot read response", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(this.context, "Request failed with " + statusCode, Toast.LENGTH_SHORT).show();
    }

}