package br.com.materialcheck.materialcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View ;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.RadioGroup;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        

    }


public void startListaMaterial(View view){
        Intent listaActivity = new Intent(this,ListaActivity.class);
        startActivity(listaActivity);

}


}
