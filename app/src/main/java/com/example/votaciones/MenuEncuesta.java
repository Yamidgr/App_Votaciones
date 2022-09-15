package com.example.votaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuEncuesta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_encuesta);
        //Oculto la actionbar
        getSupportActionBar().hide();

        Button btnVotar;
        btnVotar = findViewById(R.id.btnVotar);
        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaciones();
            }
        });
    }

    public void validaciones(){

        EditText edtNombres;
        EditText edtApellidos;
        EditText edtDocumento;
        EditText edtEdad;

        edtNombres = findViewById(R.id.edtNombres);
        edtApellidos = findViewById(R.id.edtApellidos);
        edtDocumento = findViewById(R.id.edtDocumento);
        edtEdad = findViewById(R.id.edtEdad);

        //Validacion de formularios
        if (edtNombres.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo Nombres se encuentra vacio",Toast.LENGTH_LONG).show();

        } else if (edtApellidos.getText().toString().isEmpty()){
            Toast.makeText(this, "El campo Apellidos se encuentra vacio",Toast.LENGTH_LONG).show();

        } else if (edtDocumento.getText().toString().isEmpty()){
            Toast.makeText(this, "El campo Documento se encuentra vacio",Toast.LENGTH_LONG).show();

        } else if (edtEdad.getText().toString().isEmpty() || Integer.parseInt(edtEdad.getText().toString()) < 18){
            Toast.makeText(this, "No se cumple el requisito de edad",Toast.LENGTH_LONG).show();
        }
    }
}