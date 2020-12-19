package com.example.performance2;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NotesDetails extends AppCompatActivity {
    Intent data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        //back
        ImageView imageBack = findViewById(R.id.imageBack2);
        imageBack.setOnClickListener(v -> onBackPressed());



        //intento
        data =getIntent();

        //testo
        TextView content = findViewById(R.id.noteDetailsContent);
        TextView title = findViewById(R.id.noteDetailsTitle);
        TextView subtitle = findViewById(R.id.noteDetailssubTitle);
        TextView date = findViewById(R.id.noteDate);
        content.setMovementMethod(new ScrollingMovementMethod());

        content.setText(data.getStringExtra("content"));
        title.setText(data.getStringExtra("title"));
        subtitle.setText(data.getStringExtra("subTitle"));
        date.setText(data.getStringExtra("dateTime"));

        //edit
        ImageView imageEdit = findViewById(R.id.imageEdit);
        imageEdit.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(),EditNote.class);
            i.putExtra("title",data.getStringExtra("title"));
            i.putExtra("subTitle",data.getStringExtra("subTitle"));
            i.putExtra("content",data.getStringExtra("content"));
            i.putExtra("noteId",data.getStringExtra("noteId"));
            startActivity(i);
        });
    }
}
