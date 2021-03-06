package com.example.performance2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Pattern;


public class registrati extends AppCompatActivity  {


    private Button mtregistarti;
    private TextInputLayout mEmail;
    private TextInputLayout mNome;
    private TextInputLayout mPassword;
    private FirebaseAuth fAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrati);
        //firebase
        fAuth = FirebaseAuth.getInstance();
        mNome = (TextInputLayout) findViewById(R.id.Nome);
        mEmail = (TextInputLayout) findViewById(R.id.Email);
        mPassword = (TextInputLayout) findViewById(R.id.password);
        mtregistarti = (Button) findViewById(R.id.tregistrati);
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser()!=null){
           startActivity(new Intent(getApplicationContext(),Login.class));
           finish();
        }
        //comando del bottone registrati
        mtregistarti.setOnClickListener(new View.OnClickListener() {
            @Override //controllo nell'inserimento dei dati,è importante che ci siano final e objects perchè solo in questo modo funziona
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
                progressBar.setVisibility(View.VISIBLE);
                //registrazione in firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(registrati.this, "utente creato con successo", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            finish();
                        } else {
                            Toast.makeText(registrati.this, "ERROR" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}






















