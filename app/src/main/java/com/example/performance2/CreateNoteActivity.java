package com.example.performance2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.performance2.R;
import com.example.performance2.database.NotesDatabase;
import com.example.performance2.entities.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("ALL")
public class CreateNoteActivity extends AppCompatActivity {

    private EditText inputNoteTitle, inputNoteSubTitle, inputNoteText;
    private TextView textDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(v -> onBackPressed());
        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteSubTitle = findViewById(R.id.inputNoteSubtitle);
        inputNoteText = findViewById(R.id.inputNote);
        textDateTime = findViewById(R.id.textDateTime);

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date())
        );

        ImageView imageSave = findViewById(R.id.imageSave);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

    }
   private void saveNote() {
        if (inputNoteTitle.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"il titolo  non può essere vuoto|",Toast.LENGTH_SHORT).show();
            return;
        }else if (inputNoteSubTitle.getText().toString().trim().isEmpty()
                  && inputNoteText.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"le note non possono essere vuote|",Toast.LENGTH_SHORT).show();
            return;
        }
        final Note note = new Note();
        note.setTitle(inputNoteTitle.getText().toString());
        note.setSubTitle(inputNoteSubTitle.getText().toString());
        note.setNoteText(inputNoteText.getText().toString());
        note.setDateTime(textDateTime.getText().toString());

       //noinspection deprecation
       class SaveNoteTask extends AsyncTask<Void, Void,Void>{


           @Override
           protected Void doInBackground(Void... voids) {
               NotesDatabase.getDatabase(getApplicationContext()).noteDao().insertNote(note);
               return null;
           }

           @Override
           protected void onPostExecute(Void aVoid){
               super.onPostExecute(aVoid);
               Intent intent = new Intent();
               setResult(RESULT_OK,intent);
               finish();
           }
       }
       new SaveNoteTask().execute();
   }

}