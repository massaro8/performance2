package com.example.performance2;

import android.os.Bundle;
import android.view.View;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("ALL")
public class CreateNoteActivity extends AppCompatActivity {
    FirebaseFirestore fstore;
    EditText inputNoteTitle, inputNoteSubTitle, inputNoteText;
     TextView textDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        //firebase
        fstore = FirebaseFirestore.getInstance();

        //input
        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteSubTitle = findViewById(R.id.inputNoteSubtitle);
        inputNoteText = findViewById(R.id.inputNote);
        textDateTime = findViewById(R.id.textDateTime);

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date())
        );

        //back
        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(v -> onBackPressed());

        //save
        ImageView imageSave = findViewById(R.id.imageSave);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

    }

    private void saveNote() {
        String nTitle = inputNoteTitle.getText().toString();
        String subTitle = inputNoteSubTitle.getText().toString();
        String content = inputNoteText.getText().toString();
        String dateTime = textDateTime.getText().toString();
        //empty
        if (nTitle.isEmpty()) {
            Toast.makeText(this, "il titolo  non può essere vuoto|", Toast.LENGTH_SHORT).show();
            return;
        } else if (subTitle.isEmpty()
                && content.isEmpty()) {
            Toast.makeText(this, "le note non possono essere vuote|", Toast.LENGTH_SHORT).show();
            return;
        }
        //save note firebase
        DocumentReference docref = fstore.collection("notes").document();
        Map<String, Object> note = new HashMap<>();
        note.put("title", nTitle);
        note.put("subTitle", subTitle);
        note.put("content", content);
        note.put("date",dateTime);

        docref.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(CreateNoteActivity.this, "Note aggiunte", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateNoteActivity.this, "ERRORE,riprova", Toast.LENGTH_SHORT).show();
            }
        });

    }
}










