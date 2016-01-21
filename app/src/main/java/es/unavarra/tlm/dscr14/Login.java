package es.unavarra.tlm.dscr14;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import java.io.UnsupportedEncodingException;
import cz.msebera.android.httpclient.entity.StringEntity;

public class Login extends AppCompatActivity {


    private EditText mail;
    private EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail=(EditText)findViewById(R.id.mail);
        pass=(EditText)findViewById(R.id.pass);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void hazlogin(View view) throws JsonProcessingException {
        String Mail=mail.getText().toString();
        String Pass=pass.getText().toString();
        if (Mail.equals("") || Pass.equals("")){
            Toast notificacion=Toast.makeText(this,"Por favor rellene todos los campos", Toast.LENGTH_LONG);
            notificacion.show();
        }
        //MANDA EL LOGIN AL SERVIDOR
        else{
            //convertimos lo ingresado en una cadena JSON
            UserInfoMessageLogin message = new UserInfoMessageLogin();
            message.setMail(Mail);
            message.setPass(Pass);
            String output = new ObjectMapper().writeValueAsString(message);
            AsyncHttpClient client = new AsyncHttpClient();

            try {

                client.post(this, "http://trivial.tatai.es/player/login.json", new StringEntity(output), "application/json", new WelcomeResponseLogin(this));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }



    }

}
