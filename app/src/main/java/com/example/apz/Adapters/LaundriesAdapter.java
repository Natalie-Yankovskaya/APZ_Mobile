package com.example.apz.Adapters;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apz.Fragments.WashersFragment;
import com.example.apz.Model.Laundry;
import com.example.apz.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LaundriesAdapter extends RecyclerView.Adapter<LaundriesAdapter.MyViewHolder>{
    private ArrayList<Laundry> laundriesList;
    private RecyclerViewClickListener listener;


    public LaundriesAdapter(ArrayList<Laundry> laundriesList, RecyclerViewClickListener listener) {
        this.laundriesList = laundriesList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView idLaundry;
        private TextView name;
        private TextView address;
        private TextView city;

        public MyViewHolder(final View view) {
            super(view);
            idLaundry = view.findViewById(R.id.idLaundry);
            name = view.findViewById(R.id.id);
            address = view.findViewById(R.id.model);
            city = view.findViewById(R.id.programs);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            String id = idLaundry.getText().toString();
            listener.onClick(v, getAdapterPosition());

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
        String id = laundriesList.get(position).getIdLaundry();
        String name = laundriesList.get(position).getName();
        String address = laundriesList.get(position).getAddress();
        String city = laundriesList.get(position).getCity();

        holder.idLaundry.setText(id);
        holder.name.setText(name);
        holder.address.setText(address);
        holder.city.setText(city);

    }

    @Override
    public int getItemCount() {
        return laundriesList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);

    }


}
