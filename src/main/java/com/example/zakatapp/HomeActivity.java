package com.example.zakatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == button.getId()){

            Intent intent = new Intent (this, MainActivity.class);
            startActivity(intent);
        }
    }
}
