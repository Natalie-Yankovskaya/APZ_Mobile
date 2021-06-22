package com.example.apz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.widget.TextView;

import com.example.apz.Adapters.WashersAdapter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Washer> washersList;
    private RecyclerView recyclerView;

    private TextView mTextViewResult;
    private TextView model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        washersList = new ArrayList<>();

        setWasherInfo();
        setAdapter();

        mTextViewResult = findViewById(R.id.text_view_result);



    }

    private void setAdapter() {
        WashersAdapter adapter = new WashersAdapter(washersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((getApplicationContext()));
        recyclerView.setLayoutManager((layoutManager));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter((adapter));
    }

    private void setWasherInfo() {
        String url = "https://apz-project.herokuapp.com/washers";
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override

                        public void run() {
                            mTextViewResult.setText(myResponse);
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(myResponse);
                                JSONArray posts = jsonObject.getJSONArray("data");
                                int length = posts.length();
                                for (int i = 0; i < length; i++) {
                                    JSONObject post = posts.getJSONObject(i);
                                    washersList.add(new Washer(post.getString("model")));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });


    }

}