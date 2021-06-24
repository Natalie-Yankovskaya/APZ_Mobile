package com.example.apz.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.apz.MainActivity;
import com.example.apz.Model.Washer;
import com.example.apz.R;
import com.example.apz.RegisterActivity;
import com.example.apz.StartActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddFragment extends Fragment {

    private EditText washer;
    private EditText mode;
    private EditText powder;
    private EditText user;
    private EditText conditioner;
    private Button create;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);


        washer = view.findViewById(R.id.washer);
        mode = view.findViewById(R.id.mode);
        powder = view.findViewById(R.id.powder);
        conditioner = view.findViewById(R.id.conditioner);
        create = view.findViewById(R.id.create);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txWasher = washer.getText().toString();
                String txtMode = mode.getText().toString();
                String txtPowder = powder.getText().toString();
                String txtConditioner = conditioner.getText().toString();

                String jsonWashings = "{\"washer_id\": " + txWasher + ", \"customer_id\": 1, \"status\": \"Статус\"}";
                String urlWashings = "https://apz-project.herokuapp.com/washings";
                whenPostJson_thenCorrect(urlWashings, jsonWashings);

                String jsonServices = "{\"washing_id\": 1, \"mode_id\": "+ txtMode +", " +
                        "\"washing_powder\": "+ txtPowder +"," +
                       " \"conditioner\": "+ txtConditioner +"}";
                String urlServices= "https://apz-project.herokuapp.com/services";
                whenPostJson_thenCorrect(urlServices, jsonServices);


                Fragment createdWashing = new CreatedWashing();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, createdWashing, "lalal")
                        .addToBackStack(null)
                        .commit();


            }
        });

        return view;
    }

    public void whenPostJson_thenCorrect(String url, String json) {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.Companion.create(json, JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
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
                }
            }
        });

    }




}
