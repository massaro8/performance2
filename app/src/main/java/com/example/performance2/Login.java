package com.example.performance2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {


    private Button login;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitymain();
            }

        });
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityregistrati();
            }
        });
    }


    public void openActivitymain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openActivityregistrati() {
        Intent intent1 = new Intent(this, registrati.class);
        startActivity(intent1);
    }




}

