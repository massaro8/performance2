package com.example.performance2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class Login extends AppCompatActivity {

      TextInputLayout mEmail;
     TextInputLayout mPassword;
     Button login;
    Button register;
    ProgressBar progressBar2;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//visualizza activitylogin
        mEmail = findViewById(R.id.lEmail);
        progressBar2 = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
        mPassword = findViewById(R.id.lpassword);
        login = (Button) findViewById(R.id.login);//richiama bottone login
        login.setOnClickListener(new View.OnClickListener() { //quando clicco login mi porta al comando descritto nel public
            @Override
            public void onClick(View v) {
                final String email = Objects.requireNonNull(mEmail.getEditText()).getText().toString().trim();
                final String password = Objects.requireNonNull(mPassword.getEditText()).getText().toString().trim();
                if (email.isEmpty()) {
                    mEmail.setError("inserire email");
                    return;
                }
                if (password.isEmpty()) {
                    mPassword.setError("inserire passwrd");
                    return;
                }
                if (password.length() <= 4) {
                    mPassword.setError("password corta");
                }
                progressBar2.setVisibility(View.VISIBLE);

                //autenticazione firebase
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this,"login corretto",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else {
                            Toast.makeText(Login.this,"ERRORE"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        });
        register = (Button) findViewById(R.id.register); // bottone registrati
        register.setOnClickListener(new View.OnClickListener() { //richiama bottone registrati
            @Override
            public void onClick(View v) {
                openActivityregistrati();
            }
        });
    }


  //  public void openActivitymain() { //openActivitymain();
      //  Intent intent = new Intent(this, MainActivity.class);
       // startActivity(intent);
    //}

    public void openActivityregistrati() {
        Intent intent1 = new Intent(this, registrati.class);
        startActivity(intent1);
    }




}

