package com.example.performance2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.performance2.adapters.ListNewsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("ALL")
public class fragment_news extends Fragment {


    String API_KEY = "71711b17bac84daba411ddcfcf46c0c4";
    ListView listNews;
    ProgressBar loader;


    ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
    String NEWS_SOURCE = "techrunch";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_URL = "url";
    public static final String KEY_URLTOIMAGE = "urlToImage";
    public static final String KEY_PUBLISHEDAT = "publishedAt";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_news, container, false);
        listNews = view.findViewById(R.id.listNews);
        loader = view.findViewById(R.id.loader);
        listNews.setEmptyView(loader);


        if (Function.isNetworkAvailable(getContext())) {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
        return view;
    }

     public class DownloadNews extends AsyncTask<String, Void, String> {


         @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            String xml = Function.excuteGet("https://newsapi.org/v2/top_headlines?source=" + NEWS_SOURCE + "&apiKey=" + API_KEY);
            return xml;


        }
    }
    protected void onPostExecute(String xml) {

        if (xml.length() > 10) { // Just checking if not empty

            try {
                JSONObject jsonResponse = new JSONObject(xml);
                JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HashMap<String, String> map = new HashMap<>();
                    map.put(KEY_AUTHOR, jsonObject.optString(KEY_AUTHOR));
                    map.put(KEY_TITLE, jsonObject.optString(KEY_TITLE));
                    map.put(KEY_DESCRIPTION, jsonObject.optString(KEY_DESCRIPTION));
                    map.put(KEY_URL, jsonObject.optString(KEY_URL));
                    map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE));
                    map.put(KEY_PUBLISHEDAT, jsonObject.optString(KEY_PUBLISHEDAT));
                    dataList.add(map);
                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
            }

            ListNewsAdapter adapter = new ListNewsAdapter((Activity) fragment_news.this.getContext(), dataList);
            listNews.setAdapter(adapter);

            listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent i = new Intent(fragment_news.this.getContext(), NewsDetails.class);
                    i.putExtra("url", dataList.get(+position).get(KEY_URL));
                    startActivity(i);
                }
            });

        } else {
            Toast.makeText(getContext(), "No news found", Toast.LENGTH_SHORT).show();
        }
    }
}


