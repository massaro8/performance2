package com.example.performance2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_note extends Fragment {

  public static final int REQUEST_CODE_ADD_NOTE =1;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
       View view = inflater.inflate(R.layout.nav_note,container,false);
       ImageView imageAddNoteMain = view.findViewById(R.id.imageAddNoteMain);
       imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivityForResult(
                       new Intent(getActivity(), CreateNoteActivity.class),
                       REQUEST_CODE_ADD_NOTE
               );
           }
       });
       return view;

   }



    

}








