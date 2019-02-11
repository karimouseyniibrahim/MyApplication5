package com.example.etudiantrsd.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openactiviter(View view){
        Intent intent = new Intent(MainActivity.this, ActiviterActivity.class);
        startActivity(intent);
    }
    public void openjoueur(View view){
        Intent intent = new Intent(MainActivity.this, JouerurActivity.class);
        startActivity(intent);
    }
    public void exit(View view){
        finish();
    }


}
