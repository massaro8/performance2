package com.example.performance2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class registrati extends AppCompatActivity {
    protected Button tlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrati);
        tlogin = (Button) findViewById(R.id.tlogin);
                tlogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openAcitivtytlogin();
                    }
                });
    }
    public void openAcitivtytlogin(){
        Intent intent = new Intent(this, Login.class);
                startActivity(intent);
    }
}