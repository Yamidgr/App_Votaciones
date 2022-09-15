/*
En el programa se debe implementar el código necesario para impedir que una persona
menor de edad, pueda votar. Luego de que se termine el proceso de votaciones, que se
deberá indicar por medio de un botón programado para terminar, como producto final se
espera que el programa indique por medio de la interfaz o a través de un mensaje, cuál
fue el candidato ganador y por cuántos votos ha ganado las elecciones.
*/

package com.example.votaciones;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}
