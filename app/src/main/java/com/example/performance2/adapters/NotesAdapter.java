package com.example.performance2.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.performance2.NotesDetails;
import com.example.performance2.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

     List<String> titles;
     List<String> subs;
     List<String> content;

    public NotesAdapter(List<String> title,List<String> sub,List<String>date,List<String> content ) {
        this.titles = title;this.subs=sub;this.content = content;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_container_note,
                parent,
                false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
       holder.noteTitle.setText(titles.get(position));
       holder.noteSubtitle.setText(subs.get(position));
       holder.noteContent.setText(content.get(position));



       holder.view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(v.getContext(), NotesDetails.class);
               i.putExtra("title", titles.get(position));
               i.putExtra("subTitle", subs.get(position));
               i.putExtra("content", content.get(position));
               v.getContext().startActivity(i);
           }
       });
       

    }

    @Override
    public int getItemCount() {
        return titles.size();


    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView noteTitle,noteSubtitle,noteContent;
        View view;

        NoteViewHolder(@NonNull View itemView){
            super(itemView);
           noteTitle = itemView.findViewById(R.id.textTitle);
           noteSubtitle = itemView.findViewById(R.id.textSubtitle);
           noteContent = itemView.findViewById(R.id.content);
           view = itemView;
        }

    }
}
