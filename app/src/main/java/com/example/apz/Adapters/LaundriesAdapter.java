package com.example.apz.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apz.Model.Laundry;
import com.example.apz.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LaundriesAdapter extends RecyclerView.Adapter<LaundriesAdapter.MyViewHolder>{
    private ArrayList<Laundry> laundriesList;

    public LaundriesAdapter(ArrayList<Laundry> laundriesList) {
        this.laundriesList = laundriesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView address;
        private TextView city;

        public MyViewHolder(final View view) {
            super(view);
            name = view.findViewById(R.id.textView4);
            address = view.findViewById(R.id.textView5);
            city = view.findViewById(R.id.textView6);
        }
    }

    @NonNull
    @NotNull
    @Override
    public LaundriesAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.laundries_list, parent, false);
        return new LaundriesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LaundriesAdapter.MyViewHolder holder, int position) {
        String name = laundriesList.get(position).getName();
        String address = laundriesList.get(position).getAddress();
        String city = laundriesList.get(position).getCity();

        holder.name.setText(name);
        holder.address.setText(address);
        holder.city.setText(city);

    }

    @Override
    public int getItemCount() {
        return laundriesList.size();
    }
}
