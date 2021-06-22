package com.example.apz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apz.Adapters.LaundriesAdapter;
import com.example.apz.Adapters.WashersAdapter;
import com.example.apz.Model.Laundry;
import com.example.apz.R;
import com.example.apz.Washer;

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

public class LaundriesFragment extends Fragment {

    private ArrayList<Laundry> laundriesList;
    private RecyclerView recyclerView;
    private TextView textView3;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_laundries, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        laundriesList = new ArrayList<>();
        textView3 = view.findViewById(R.id.textView3);


        setWasherInfo();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        LaundriesAdapter adapter = new LaundriesAdapter(laundriesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((getContext()));
        recyclerView.setLayoutManager((layoutManager));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter((adapter));
    }

    private void setWasherInfo() {
        String url = "https://apz-project.herokuapp.com/laundries";
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

                    LaundriesFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override

                        public void run() {

                            JSONObject jsonObject = null;
                            try {
                                textView3.setText("Laundries");
                                jsonObject = new JSONObject(myResponse);
                                JSONArray posts = jsonObject.getJSONArray("data");
                                int length = posts.length();
                                for (int i = 0; i < length; i++) {
                                    JSONObject post = posts.getJSONObject(i);
                                    String name = post.getString("name");
                                    String address = post.getString("address");
                                    String city = post.getString("city");

                                    Laundry laundry = new Laundry(name, address, city);

                                    laundriesList.add(laundry);
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