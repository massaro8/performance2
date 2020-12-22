package com.example.performance2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class fragment_note extends Fragment {



    public fragment_note(){

 }




    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int RESULT_OK = -1;



    RecyclerView notesRecyclerView;
    FirebaseFirestore fstore;
    FirestoreRecyclerAdapter<Note,NotesViewHolder> noteAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_note, container, false);
         //pagina iniziale
         List<String> titles = new ArrayList<>();
        List<String> subs = new ArrayList<>();
         List<String> content = new ArrayList<>();



        //firebase
       fstore = FirebaseFirestore.getInstance();

        Query query = fstore.collection("notes").orderBy("title",Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Note> allNotes = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query,Note.class)
                .build();

         //noteadapter
         noteAdapter = new FirestoreRecyclerAdapter<Note,NotesViewHolder>(allNotes) {

             @Override
             protected void onBindViewHolder(@NonNull NotesViewHolder notesViewHolder, int i, @NonNull Note note) {
                 notesViewHolder.noteTitle.setText(note.getSubTitle());
                 notesViewHolder.noteSubtitle.setText(note.getSubTitle());
                 notesViewHolder.noteContent.setText(note.getContent());
                 String docId = noteAdapter.getSnapshots().getSnapshot(i).getId();



                 notesViewHolder.view.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent i = new Intent(v.getContext(), NotesDetails.class);
                         i.putExtra("title", note.getTitle());
                         i.putExtra("subTitle", note.getSubTitle());
                         i.putExtra("content", note.getContent());
                         i.putExtra("noteId",docId);
                         v.getContext().startActivity(i);
                     }
                 });
                 //menuIcon
                 ImageView menuIcon = notesViewHolder.view.findViewById(R.id.menuIcon);
                 menuIcon.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         String docId = noteAdapter.getSnapshots().getSnapshot(i).getId();
                         PopupMenu menu = new PopupMenu(v.getContext(),v);
                         menu.getMenu().add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                             @Override
                             public boolean onMenuItemClick(MenuItem item) {
                                 Intent i = new Intent(view.getContext(),EditNote.class);
                                 i.putExtra("title",note.getTitle());
                                 i.putExtra("subTitle",note.getSubTitle());
                                 i.putExtra("content",note.getContent());
                                 i.putExtra("noteId",docId);
                                 startActivity(i);
                                 return false;
                             }
                         });
                         menu.getMenu().add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                             @Override
                             public boolean onMenuItemClick(MenuItem item) {
                                 DocumentReference docRef = fstore.collection("notes").document(docId);
                                 docRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                     @Override
                                     public void onSuccess(Void aVoid) {
                                         //note deleted
                                     }
                                 }).addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(@NonNull Exception e) {
                                         Toast.makeText(fragment_note.this.getContext(), "ERRORE,riprova", Toast.LENGTH_SHORT).show();
                                     }
                                 });
                                 return false;
                             }
                         });
                         menu.show();
                     }
                 });
             }
             @NonNull
             @Override
             public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 View view = LayoutInflater.from(parent.getContext()).inflate(
                         R.layout.item_container_note,
                         parent,
                         false);
                 return new NotesViewHolder(view);
             }


         };


        //imageview
         ImageView imageAddNoteMain = view.findViewById(R.id.imageAddNoteMain);
        imageAddNoteMain.setOnClickListener(v -> {
            startActivityForResult(
                    new Intent(getActivity(), CreateNoteActivity.class),
                    REQUEST_CODE_ADD_NOTE
            );
        });
        //recyclerView

        notesRecyclerView = view.findViewById(R.id.notesRecyclerView);
        notesRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );
        notesRecyclerView.setAdapter(noteAdapter);
        return view;

    }
    public class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView noteTitle,noteSubtitle,noteContent;
        View view;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.textTitle);
            noteSubtitle = itemView.findViewById(R.id.textSubtitle);
            noteContent = itemView.findViewById(R.id.content);
            view = itemView;
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        noteAdapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        if (noteAdapter !=null){
            noteAdapter.startListening();
        }
    }
    }












