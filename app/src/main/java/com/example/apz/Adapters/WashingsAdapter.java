package com.example.apz.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apz.Model.Washer;
import com.example.apz.Model.Washing;
import com.example.apz.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WashingsAdapter extends RecyclerView.Adapter<WashingsAdapter.MyViewHolder>{

    private ArrayList<Washing> washingsList ;

    public WashingsAdapter(ArrayList<Washing> washingsList){
        this.washingsList = washingsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView status;
        private TextView mode;
        private TextView conditioner;
        private TextView price;

        public MyViewHolder(final View view) {
            super(view);
            status = view.findViewById(R.id.status);
            mode = view.findViewById(R.id.mode);
            price = view.findViewById(R.id.powder);
            conditioner = view.findViewById(R.id.conditioner);

        }
    }

    @NonNull
    @NotNull
    @Override
    public WashingsAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        return new WashingsAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull WashingsAdapter.MyViewHolder holder, int position) {

        String status = washingsList.get(position).getStatus();
        String mode = washingsList.get(position).getMode();
        String price = washingsList.get(position).getPrice();
        String conditioner = washingsList.get(position).getConditioner();

        holder.status.setText(status);
        holder.mode.setText(mode);
        holder.price.setText(price);
        holder.conditioner.setText(conditioner);

    }

    @Override
    public int getItemCount() {
        return washingsList.size();
    }
}
