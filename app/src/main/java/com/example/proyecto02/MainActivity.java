package com.example.proyecto02;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity{

    RadioButton r1, r2, r3, r4, r5, r6;
    String opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r1=(RadioButton)findViewById(R.id.radioButton1);
        r2=(RadioButton)findViewById(R.id.radioButton2);
        r3=(RadioButton)findViewById(R.id.radioButton3);
        r4=(RadioButton)findViewById(R.id.radioButton4);
        r5=(RadioButton)findViewById(R.id.radioButton5);
        r6=(RadioButton)findViewById(R.id.radioButton6);

    }

    public void Siguiente(View view){
        if(r1.isChecked()==true){
            opcion="1";
        }else if(r2.isChecked()==true){
            opcion="2";
        }else if(r3.isChecked()==true){
            opcion="3";
        }else if(r4.isChecked()==true){
            opcion="4";
        }else if(r5.isChecked()==true){
            opcion="5";
        }else if(r6.isChecked()==true){
            opcion="6";
        }else{
            opcion="0";
        }
        Intent siguiente = new Intent(this, Medicion.class);
        siguiente.putExtra("opcionKey",opcion);
        startActivity(siguiente);
    }



}