package com.example.performance2;

import android.os.Bundle;
import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;


    }
}