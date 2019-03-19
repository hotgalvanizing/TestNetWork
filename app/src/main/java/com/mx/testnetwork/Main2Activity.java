package com.mx.testnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3,button5,button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button1:
                intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.button2:
                intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
                break;

            case R.id.button3:
                intent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(intent);
                break;

            case R.id.button5:
                intent = new Intent(Main2Activity.this, Main5Activity.class);
                startActivity(intent);
                break;

            case R.id.button6:
                intent = new Intent(Main2Activity.this, Main6Activity.class);
                startActivity(intent);
                break;



            default:
                break;
        }
    }
}
