package com.example.performance2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.performance2.adapters.NotesAdapter;
import com.example.performance2.entities.Note;

import java.util.ArrayList;
import java.util.List;

import static com.example.performance2.database.NotesDatabase.getDatabase;

@SuppressWarnings("ALL")
public class fragment_note extends Fragment {



    public fragment_note(){

 }




    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int RESULT_OK = -1;

    List<Note> noteList;
    NotesAdapter notesAdapter;
    RecyclerView notesRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_note, container, false);
        //imageview
         ImageView imageAddNoteMain = view.findViewById(R.id.imageAddNoteMain);
        imageAddNoteMain.setOnClickListener(v -> startActivityForResult(
                new Intent(getActivity(), CreateNoteActivity.class),
                REQUEST_CODE_ADD_NOTE
        ));
        //recyclerView
        notesRecyclerView = view.findViewById(R.id.notesRecyclerView);
        notesRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );


        noteList = new ArrayList<>();
        notesAdapter = new NotesAdapter(noteList);
        notesRecyclerView.setAdapter(notesAdapter);
        startAsycnc();
        return view;

    }

    public void startAsycnc(){
         new StartAsycncTask().execute();
    }
    public  class StartAsycncTask extends AsyncTask<Void, Void, List<Note>>{


        @Override
        protected List<Note> doInBackground(Void... voids) {
            return getDatabase(getContext()).noteDao().getAllNotes();
        }
        protected void onPostExecute(List<Note> notes){
            super.onPostExecute(notes);
            if (noteList.size() == 0){
                noteList.addAll(notes);
                notesAdapter.notifyDataSetChanged();
            }else {
                noteList.add(0,notes.get(0));
                notesAdapter.notifyItemInserted(0);
            }
            notesRecyclerView.smoothScrollToPosition(0);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_CODE_ADD_NOTE) && (resultCode == RESULT_OK)){
           startAsycnc();
        }
    }
}









