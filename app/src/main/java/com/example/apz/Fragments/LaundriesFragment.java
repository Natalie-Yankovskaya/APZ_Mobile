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
import com.example.apz.Model.Laundry;
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

public class LaundriesFragment extends Fragment {

    private ArrayList<Laundry> laundriesList;
    private RecyclerView recyclerView;
    private LaundriesAdapter.RecyclerViewClickListener listener;
    private TextView titleProfile;
    private TextView idLaundry;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_laundries, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        laundriesList = new ArrayList<>();
        titleProfile = view.findViewById(R.id.titleCreated);
        idLaundry = view.findViewById(R.id.idLaundry);
        Fragment washers;

        setWasherInfo();
        setAdapter();
        return view;
    }




    private void setAdapter() {
        setOnClickListener();
        LaundriesAdapter adapter = new LaundriesAdapter(laundriesList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((getContext()));
        recyclerView.setLayoutManager((layoutManager));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter((adapter));
    }

    private void setOnClickListener() {
        listener = new LaundriesAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Intent intent = new Intent(getActivity(), ProfileFragment.class);
                //startActivity(intent);
//                String id = idLaundry.getText().toString();

                String id = "1";

                Fragment washers= new WashersFragment();
//
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                washers.setArguments(bundle);


                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, washers, "lalal")
                        .addToBackStack(null)
                        .commit();

            }
        };
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
                                titleProfile.setText("Laundries");
                                jsonObject = new JSONObject(myResponse);
                                JSONArray posts = jsonObject.getJSONArray("data");
                                int length = posts.length();
                                for (int i = 0; i < length; i++) {
                                    JSONObject post = posts.getJSONObject(i);
                                    String id = post.getString("id");
                                    String name = post.getString("name");
                                    String address = post.getString("address");
                                    String city = post.getString("city");

                                    Laundry laundry = new Laundry(id, name, address, city);

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