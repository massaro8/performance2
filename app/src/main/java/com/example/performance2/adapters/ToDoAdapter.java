package com.example.performance2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.performance2.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<String> Texts;
    private List<Boolean> checkeds;


    public ToDoAdapter(List<String> Text,List<Boolean> checked){
        this.Texts=Text;this.checkeds=checked;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklayout,parent,false);
        return new ViewHolder(view);

    }
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.task.setText(Texts.get(position));



    }
    public int getItemCount(){
        return Texts.size();
    }
    private boolean toBoolean(int n){
        return n!=0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todocheckbox);
        }
    }

}
