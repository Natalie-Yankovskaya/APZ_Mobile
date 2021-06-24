package com.example.apz.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apz.Adapters.WashersAdapter;
import com.example.apz.Model.Washer;
import com.example.apz.R;

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

public class WashersFragment extends Fragment {

    private ArrayList<Washer> washersList;
    private RecyclerView recyclerView;

    private TextView textView3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_laundries, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        washersList = new ArrayList<>();
        textView3 = view.findViewById(R.id.titleCreated);


        Fragment washers;


        setWasherInfo();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        WashersAdapter adapter = new WashersAdapter(washersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((getContext()));
        recyclerView.setLayoutManager((layoutManager));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter((adapter));
    }

    private void setWasherInfo() {


        Bundle bundle = this.getArguments();
        String i = bundle.getString("id");
        //textView3.setText(i);

        String url = "https://apz-project.herokuapp.com/washers?laundry_id=" + i;
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

                    WashersFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override

                        public void run() {
                            textView3.setText("Washers");
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(myResponse);
                                JSONArray posts = jsonObject.getJSONArray("data");
                                int length = posts.length();
                                for (int i = 0; i < length; i++) {
                                    JSONObject post = posts.getJSONObject(i);
                                    String id = post.getString("id");
                                    String model = post.getString("model");
                                    String program = "3";
                                    String size = "Велика";

                                    washersList.add(new Washer(id, model, program, size));
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
