package com.example.performance2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNewTask extends AppCompatActivity {
    FirebaseFirestore fstore;
    EditText newtasktext;
    TextView textDateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtask);

        //firebase
        fstore = FirebaseFirestore.getInstance();

        //input
        newtasktext = findViewById(R.id.newtasktext);
        textDateTime = findViewById(R.id.textDateTime);


        //back
        ImageView imageBack = findViewById(R.id.imageBack4);
        imageBack.setOnClickListener(v -> onBackPressed());

        //save
        Button imageSave = findViewById(R.id.newtaskbutton);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });

    }
    private void saveTask() {
        String Text = newtasktext.getText().toString();

        //empty
        if (Text.isEmpty()) {
            Toast.makeText(this, "il titolo  non può essere vuoto|", Toast.LENGTH_SHORT).show();
            return;
        }
        //save task firebase
        DocumentReference docref = fstore.collection("tasks").document();
        Map<String, Object> tasks = new HashMap<>();
        tasks.put("Task", Text);



        docref.set(tasks).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddNewTask.this, "task aggiunte", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNewTask.this, "ERRORE,riprova", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

