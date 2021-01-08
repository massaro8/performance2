package com.example.performance2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.performance2.R;
import com.example.performance2.fragment_todolist;

import java.util.List;

@SuppressWarnings("ALL")
public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    List<String> titles;
    fragment_todolist activity;


    public ToDoAdapter(List<String> titles) {
        this.titles = titles;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklayout,parent,false);
        return new ViewHolder(view);

    }
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.task.setText(titles.get(position));



    }
    public int getItemCount(){
        return titles.size();
    }
    public fragment_todolist getContext() {
        return activity;
    }
    @Override
    public int getItemViewType(int position){
        return position;
    }
    private boolean toBoolean(int n){
        return n!=0;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;


        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todocheckbox);

        }
    }

}
