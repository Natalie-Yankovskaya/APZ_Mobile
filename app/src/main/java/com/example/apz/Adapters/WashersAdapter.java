package com.example.apz.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apz.R;
import com.example.apz.Washer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WashersAdapter extends RecyclerView.Adapter<WashersAdapter.MyViewHolder>{
    private ArrayList<Washer> washerList;

    public WashersAdapter(ArrayList<Washer> washerList){
        this.washerList = washerList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView modelTxt;

        public MyViewHolder(final View view) {
            super(view);
            modelTxt = view.findViewById(R.id.textView);
        }
    }
    @NonNull
    @NotNull
    @Override
    public WashersAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WashersAdapter.MyViewHolder holder, int position) {
        String model = washerList.get(position).getModel();
        holder.modelTxt.setText(model);
    }

    @Override
    public int getItemCount() {
        return washerList.size();
    }
}
