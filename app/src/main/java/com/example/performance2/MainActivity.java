package com.example.performance2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.AsyncTaskLoader;

import com.example.performance2.database.NotesDatabase;
import com.example.performance2.entities.Note;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int REQUEST_CODE_ADD_NOTE=1;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
     NavigationView navigationView;
    FragmentManager fragmentManager;
   FragmentTransaction fragmentTransaction;
   ActionBarDrawerToggle actionBarDrawerToggle;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle  = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();



        // carica il fragmwnt predefinito
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment,new MainFragment());
        fragmentTransaction.commit();

        //databse note
        getNotes();




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;




    }
    private void getNotes(){
        //noinspection deprecation
        @SuppressWarnings("deprecation")
        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void,List<Note>> {


            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase.getDatabase(getApplicationContext()).noteDao().getAllNotes();

            }

            @Override
            protected void onPostExecute(List<Note> notes){
                super.onPostExecute(notes);
                Log.d("MY_NOTES",notes.toString());
            }
        }
        new GetNotesTask().execute();
    }
    public void log_out(MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(menuitem.getItemId() == R.id.home){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new MainFragment());
            fragmentTransaction.commit();


        }
        if (menuitem.getItemId() == R.id.note){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new fragment_note ());
            fragmentTransaction.commit();

        }
        if (menuitem.getItemId() == R.id.news){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new fragment_news());
            fragmentTransaction.commit();

        }
        if (menuitem.getItemId() == R.id.to_do_list){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new fragment_todolist ());
            fragmentTransaction.commit();

        }
        if (menuitem.getItemId() == R.id.upload){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new fragment_upload ());
            fragmentTransaction.commit();

        }
        if (menuitem.getItemId() == R.id.calendario){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new fragment_calendario ());
            fragmentTransaction.commit();

        }
       
        return false;
    }




}