/*
En el programa se debe implementar el código necesario para impedir que una persona
menor de edad, pueda votar. Luego de que se termine el proceso de votaciones, que se
deberá indicar por medio de un botón programado para terminar, como producto final se
espera que el programa indique por medio de la interfaz o a través de un mensaje, cuál
fue el candidato ganador y por cuántos votos ha ganado las elecciones.
*/

package com.example.votaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Oculto la actionbar
        getSupportActionBar().hide();

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor filas = db.rawQuery("SELECT * FROM tbl_candidatos", null);
        if (!filas.moveToFirst()){
            db.execSQL("INSERT INTO tbl_candidatos values (null, 'En Blanco'),(null,'Carlos'), (null,'Pedro')");
        }
        filas.close();
        db.close();

        Button btnVotar;
        Button btnNueva;
        btnVotar = findViewById(R.id.btnVotar);
        btnNueva = findViewById(R.id.btnNueva);

        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MenuEncuesta.class);
                startActivity(intent);
            }
        });

        btnNueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOpenHelper dbHelper = new MyOpenHelper(v.getContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db != null) {
                    db.execSQL("DELETE FROM tbl_votos");
                    db.execSQL("DELETE FROM tbl_personas");
                    db.execSQL("DELETE FROM tbl_candidatos");
                    Toast.makeText(v.getContext(), "Base de datos reiniciada", Toast.LENGTH_LONG).show();
                    db.execSQL("INSERT INTO tbl_candidatos values (null, 'En Blanco'),(null,'Carlos'), (null,'Pedro')");
                    Toast.makeText(v.getContext(), "Candidatos Registrados", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(v.getContext(), MenuEncuesta.class);
                startActivity(intent);
            }
        });
    }
}
