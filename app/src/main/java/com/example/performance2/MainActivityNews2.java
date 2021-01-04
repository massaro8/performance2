package com.example.performance2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.performance2.adapters.Adapter;
import com.example.performance2.models.Article;
import com.example.performance2.models.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class MainActivityNews2 extends AppCompatActivity {
    RecyclerView recyclerView;
    final String API_KEY ="71711b17bac84daba411ddcfcf46c0c4";
    Adapter adapter;
    List<Article> articles= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.nav_news);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String country = getCountry();
        retrieveJson(country,API_KEY);

    }
    public void retrieveJson(String country,String apiKey){
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() &&response.body().getArticles() !=null){
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(MainActivityNews2.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(MainActivityNews2.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    public String getCountry(){
        Locale locale=Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
