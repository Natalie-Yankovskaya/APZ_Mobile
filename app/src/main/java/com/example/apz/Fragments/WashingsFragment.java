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

import com.example.apz.Adapters.LaundriesAdapter;
import com.example.apz.Adapters.WashingsAdapter;
import com.example.apz.Model.Laundry;
import com.example.apz.Model.Washing;
import com.example.apz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WashingsFragment extends Fragment {

    private ArrayList<Washing> washingsList;
    private RecyclerView recyclerView;
    private TextView titleHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        washingsList = new ArrayList<>();
        titleHistory = view.findViewById(R.id.titleHistory);
        Fragment washings;

        setWasherInfo();
        setAdapter();
        return view;
    }

    private void setAdapter() {

        WashingsAdapter adapter = new WashingsAdapter(washingsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((getContext()));
        recyclerView.setLayoutManager((layoutManager));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter((adapter));

    }

    private void setWasherInfo() {
        String url = "https://apz-project.herokuapp.com/washings?customer_id=1";
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

                    WashingsFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override

                        public void run() {

                            JSONObject jsonObject = null;
                            try {
                                titleHistory.setText("Washings");
                                jsonObject = new JSONObject(myResponse);
                                JSONArray posts = jsonObject.getJSONArray("data");
                                int length = posts.length();
                                for (int i = 0; i < length; i++) {
                                    JSONObject post = posts.getJSONObject(i);

                                    Washing washing = new Washing(post.getString("status"),
                                            "mode",
                                            "conditioner",
                                            "20");

                                    washingsList.add(washing);
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