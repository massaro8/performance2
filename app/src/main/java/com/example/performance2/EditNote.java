package com.example.performance2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class EditNote extends AppCompatActivity {
    Intent data;
    EditText editNoteTitle,editNoteContent,editSubTitle;
    FirebaseFirestore fstore;

    @Override
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_note);
    //firebase
        fstore = FirebaseFirestore.getInstance();
    //data
    data = getIntent();
    //edittext
     editNoteContent =findViewById(R.id.editNoteContent);
     editNoteTitle = findViewById(R.id.editNoteTitle);
     editSubTitle = findViewById(R.id.editNoteSubtitle);


    String noteTitle = data.getStringExtra("title");
    String noteContent = data.getStringExtra("content");
    String noteSubTitle = data.getStringExtra("subTitle");

    editNoteTitle.setText(noteTitle);
    editSubTitle.setText(noteSubTitle);
    editNoteContent.setText(noteContent);

        //edit
        ImageView imageEdit = findViewById(R.id.imageSave2);
        imageEdit.setOnClickListener(view -> {
            String nTitle = editNoteTitle.getText().toString();
            String subTitle = editSubTitle.getText().toString();
            String Text = editNoteContent.getText().toString();
            //empty
            if (nTitle.isEmpty()) {
                Toast.makeText(EditNote.this, "il titolo  non può essere vuoto|", Toast.LENGTH_SHORT).show();
                return;
            } else if (subTitle.isEmpty()
                    && Text.isEmpty()) {
                Toast.makeText(EditNote.this, "le note non possono essere vuote|", Toast.LENGTH_SHORT).show();
                return;
            }
            //save note firebase
            DocumentReference docref = fstore.collection("notes").document(data.getStringExtra("noteId"));

            Map<String, Object> note = new HashMap<>();
            note.put("title", nTitle);
            note.put("subTitle", subTitle);
            note.put("content", Text);

            docref.update(note).addOnSuccessListener(aVoid -> {
                Toast.makeText(EditNote.this, "Note modificate", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EditNote.this, "ERRORE,riprova", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}