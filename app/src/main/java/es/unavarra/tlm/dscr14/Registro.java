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

public class Registro extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        email=(EditText)findViewById(R.id.email);
        nombre=(EditText)findViewById(R.id.nombre);
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

    public void registrar(View view) throws JsonProcessingException{
        String Name=nombre.getText().toString();
        String Mail=email.getText().toString();
        String Pass=pass.getText().toString();
        if (Name.equals("") || Mail.equals("") || Pass.equals("")){
            Toast notificacion=Toast.makeText(this,"Por favor rellene todos los campos", Toast.LENGTH_LONG);
            notificacion.show();
        }
        //MANDA EL REGISTRO AL SERVIDOR
        else {
            //convertimos lo ingresado en una cadena JSON
            UserInfoMessage message = new UserInfoMessage();
            message.setMail(Mail);
            message.setName(Name);
            message.setPass(Pass);

            String output = new ObjectMapper().writeValueAsString(message);
            //Toast notificacion = Toast.makeText(this, output, Toast.LENGTH_LONG);
            //notificacion.show();
            AsyncHttpClient client = new AsyncHttpClient();

            try {

              client.post(this, "http://trivial.tatai.es/player/register.json", new StringEntity(output), "application/json", new WelcomeResponseRegistro(this));
           } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            finish();
        }
    }

}
