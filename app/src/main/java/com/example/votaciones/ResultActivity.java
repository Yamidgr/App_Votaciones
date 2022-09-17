package com.example.votaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    MyOpenHelper dbHelper = new MyOpenHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        TextView tvGanador;
        tvGanador = findViewById(R.id.tvGanador);
        tvGanador.setText("");



        Cursor mCount= db.rawQuery("select count(*) from tbl_votos inner join tbl_candidatos where tbl_candidatos.name='En Blanco' and tbl_votos.id_candidato = tbl_candidatos.id", null);
        mCount.moveToFirst();
        int Blanco= mCount.getInt(0);

        mCount= db.rawQuery("select count(*) from tbl_votos inner join tbl_candidatos where tbl_candidatos.name='Carlos' and tbl_votos.id_candidato = tbl_candidatos.id", null);
        mCount.moveToFirst();
        int Carlos= mCount.getInt(0);

        mCount= db.rawQuery("select count(*) from tbl_votos inner join tbl_candidatos where tbl_candidatos.name='Pedro' and tbl_votos.id_candidato = tbl_candidatos.id", null);
        mCount.moveToFirst();
        int Pedro= mCount.getInt(0);

        mCount.close();

        if (Blanco > Carlos && Blanco > Pedro) {
            tvGanador.setText("Gana Blanco con " + Blanco + " Votos");
        } else if (Carlos > Blanco && Carlos > Pedro) {
            tvGanador.setText("Gana Carlos con " + Carlos + " Votos");
        } else {
            tvGanador.setText("Gana Pedro con " + Pedro + " Votos");
        }

        //tvGanador.setText("Blanco " + Blanco + " Carlos "+ Carlos + "Pedro " + Pedro);

    }
}

/*
    Cursor mCount= db.rawQuery("select count(*) from users where uname='" + loginname + "' and pwd='" + loginpass +"'", null);
    mCount.moveToFirst();
    int count= mCount.getInt(0);
    mCount.close();
*/
