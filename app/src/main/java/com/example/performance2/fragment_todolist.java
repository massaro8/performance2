package com.example.performance2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class fragment_todolist extends Fragment {


    public fragment_todolist(){

    }

    public static final int REQUEST_CODE_ADD_TASK = 1;


    RecyclerView tasksReciclerView;
    FirebaseFirestore fstore;
    FirestoreRecyclerAdapter<ToDoModel, fragment_todolist.TaskViewHolder> tasksAdapter;





    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_to_do_list, container, false);
         //liste
        List<String> titles = new ArrayList<>();



        //firebase
        fstore = FirebaseFirestore.getInstance();

        Query query = fstore.collection("tasks").orderBy("titles", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ToDoModel> allTask = new FirestoreRecyclerOptions.Builder<ToDoModel>()
                .setQuery(query, ToDoModel.class)
                .build();

        //noteadapter
        tasksAdapter = new FirestoreRecyclerAdapter<ToDoModel, fragment_todolist.TaskViewHolder>(allTask) {


            @Override
            protected void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i, @NonNull ToDoModel toDoModel) {
                taskViewHolder.task.setText(toDoModel.getTitles());
                String docId = tasksAdapter.getSnapshots().getSnapshot(i).getId();




            }


            @NonNull
            @Override
            public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.tasklayout,
                        parent,
                        false);
                return new TaskViewHolder(view);
            }


        };

        //imageview
        FloatingActionButton fabTask = view.findViewById(R.id.floatingactionbutton);
                fabTask.setOnClickListener((View v) -> {
                    startActivityForResult(
                            new Intent(getActivity(), AddNewTask.class),
                            REQUEST_CODE_ADD_TASK
                    );
                });

                //reciclerview
         tasksReciclerView = view.findViewById(R.id.taskReciclerView);
         tasksReciclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         tasksReciclerView.setAdapter(tasksAdapter);


        return view;
    }
    public  class TaskViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
           task = itemView.findViewById(R.id.todocheckbox);

        }
    }
    @Override
    public void onStart(){
        super.onStart();
        tasksAdapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        if (tasksAdapter !=null){
            tasksAdapter.startListening();
        }
    }
}

