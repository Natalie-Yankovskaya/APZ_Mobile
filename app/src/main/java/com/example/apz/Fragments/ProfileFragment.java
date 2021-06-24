package com.example.apz.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class ProfileFragment extends Fragment {

    private TextView name;
    private TextView surname;
    private TextView email;
    private TextView titleProfile;
    private ImageView logOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        titleProfile = view.findViewById(R.id.titleCreated);
        name = view.findViewById(R.id.laundry);
        surname = view.findViewById(R.id.washer);
        email = view.findViewById(R.id.mode);
        logOut = view.findViewById(R.id.logout);

        setProfileInfo();


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), StartActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void setProfileInfo() {

//        Bundle bundle = this.getArguments();
//        String i = bundle.getString("id");
        //textView3.setText(i);

        String url = "https://apz-project.herokuapp.com/customers/1";
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

                    ProfileFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override

                        public void run() {
                            titleProfile.setText("Profile");
                            JSONObject jsonObject = null;
                            JSONObject jsonObjectData = null;
                            try {
                                jsonObject = new JSONObject(myResponse);

                                String data = jsonObject.getString("data");
                                jsonObjectData = new JSONObject(data);
                               // String emailTxt = jsonObjectData.getString("email");

                                name.setText(jsonObjectData.getString("name"));
                                surname.setText(jsonObjectData.getString("surname"));
                                email.setText("emailnure.ua");

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
