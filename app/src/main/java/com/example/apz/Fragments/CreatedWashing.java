package com.example.apz.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.apz.R;
import com.example.apz.StartActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CreatedWashing extends Fragment {

    private TextView info;
    private TextView titleCreated;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_created, container, false);
        info = view.findViewById(R.id.info);
        titleCreated = view.findViewById(R.id.titleCreated);

        setWashingInfo();

        return view;
    }

    private void setWashingInfo() {

//        Bundle bundle = this.getArguments();
//        String i = bundle.getString("id");
        //textView3.setText(i);

        String url = "https://apz-project.herokuapp.com/services/1";
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

                    CreatedWashing.this.getActivity().runOnUiThread(new Runnable() {
                        @Override

                        public void run() {
                            titleCreated.setText("Created");
                            info.setText(myResponse);
                            JSONObject jsonObject = null;
                            JSONObject jsonObjectData = null;
//                            try {
//                                jsonObject = new JSONObject(myResponse);
//
//                                try {
//                                    jsonObject = new JSONObject(myResponse);
//
//                                    String data = jsonObject.getString("status");
//                                    jsonObjectData = new JSONObject(data);
//                                    // String emailTxt = jsonObjectData.getString("email");
//
//                                    info.setText(data);
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }

                        }
                    });
                }
            }
        });

    }
}
