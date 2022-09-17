package com.example.votaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuEncuesta extends AppCompatActivity {
    MyOpenHelper dbHelper = new MyOpenHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_encuesta);
        //Oculto la actionbar
        //getSupportActionBar().hide();

        EditText edtNombres;
        EditText edtApellidos;
        EditText edtDocumento;
        EditText edtEdad;
        Spinner spCandidatos;

        edtNombres = findViewById(R.id.edtNombres);
        edtApellidos = findViewById(R.id.edtApellidos);
        edtDocumento = findViewById(R.id.edtDocumento);
        edtEdad = findViewById(R.id.edtEdad);
        spCandidatos = findViewById(R.id.spCandidatos);

        List<Candidatos> candidatos = llenarCandidatos();
        ArrayAdapter<Candidatos> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, candidatos);
        spCandidatos.setAdapter(adapter);

        Button btnVotar;
        btnVotar = findViewById(R.id.btnVotar);
        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean requisitos = validaciones(edtNombres, edtApellidos, edtDocumento, edtEdad);
                if(requisitos) {
                    int idPersona = 99999999;
                    idPersona = registrarPersona(edtNombres, edtApellidos, edtDocumento, edtEdad);
                    if(idPersona != 99999999) {
                        boolean registroVoto = registrarVoto(spCandidatos);
                        if (registroVoto) {
                            Toast.makeText(v.getContext(), "Voto registrado con exito", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    public boolean validaciones(EditText edtNombres, EditText edtApellidos, EditText edtDocumento, EditText edtEdad){

        //Validacion de formularios
        if (edtNombres.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo Nombres se encuentra vacio",Toast.LENGTH_LONG).show();
            return false;

        } else if (edtApellidos.getText().toString().isEmpty()){
            Toast.makeText(this, "El campo Apellidos se encuentra vacio",Toast.LENGTH_LONG).show();
            return false;

        } else if (edtDocumento.getText().toString().isEmpty()){
            Toast.makeText(this, "El campo Documento se encuentra vacio",Toast.LENGTH_LONG).show();
            return false;

        } else if (edtEdad.getText().toString().isEmpty() || Integer.parseInt(edtEdad.getText().toString()) < 18){
            Toast.makeText(this, "No se cumple el requisito de edad",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public int registrarPersona(EditText edtNombres, EditText edtApellidos, EditText edtDocumento, EditText edtEdad){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int id_persona = 0;
        if (db != null) {
            // Insert con ContentValues
            ContentValues data = new ContentValues();

            data.put("name", edtNombres.getText().toString() + " " + edtApellidos.getText().toString());
            data.put("document", Integer.parseInt(edtDocumento.getText().toString()));
            data.put("age", Integer.parseInt(edtEdad.getText().toString()));
            id_persona = (int) db.insert("tbl_personas", null, data);
            Toast.makeText(this, "Voto Registrado", Toast.LENGTH_LONG).show();
            return id_persona;
        }
        return id_persona;
    }
    public boolean registrarVoto(Spinner spCandidatos){

        int idcan = ((Candidatos)spCandidatos.getSelectedItem()).getId();
        Toast.makeText(this, String.valueOf(idcan), Toast.LENGTH_LONG).show();
        return false;
    }

    public Cursor mostrarCandidatos() {
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor filas = db.rawQuery("SELECT * FROM tbl_candidatos", null);
            if (filas.moveToFirst()){
                return filas;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    private List<Candidatos> llenarCandidatos() {
        List<Candidatos> listaCandidatos = new ArrayList<>();
        Cursor cursor = mostrarCandidatos();
        if(cursor != null) {
            if(cursor.moveToFirst()){
                do {
                    Candidatos can = new Candidatos();
                    can.setId(cursor.getInt(0));
                    can.setName(cursor.getString(1));
                    listaCandidatos.add(can);
                } while(cursor.moveToNext());
            }
        }
        return listaCandidatos;
    }
}
