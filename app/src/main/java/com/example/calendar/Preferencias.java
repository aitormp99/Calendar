package com.example.calendar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Preferencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);



        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new com.example.calendar.AjustesFragment()).commit();
    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        reload();
    }

    public void reload(){
        Intent intent = new Intent();
        intent.putExtra("tarea",'b');
        setResult(RESULT_OK,intent);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent register = new Intent(getBaseContext(),MainActivity.class);
        startActivity(register);
    }

}