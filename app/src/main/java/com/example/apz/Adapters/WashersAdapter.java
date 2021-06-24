package com.example.apz.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apz.R;
import com.example.apz.Model.Washer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WashersAdapter extends RecyclerView.Adapter<WashersAdapter.MyViewHolder>{
    private ArrayList<Washer> washerList;

    public WashersAdapter(ArrayList<Washer> washerList){
        this.washerList = washerList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView id;
        private TextView model;
        private TextView program;
        private TextView size;

        public MyViewHolder(final View view) {
            super(view);
            id = view.findViewById(R.id.id);
            model = view.findViewById(R.id.model);
            program = view.findViewById(R.id.programs);
            size = view.findViewById(R.id.size);

        }
    }
    @NonNull
    @NotNull
    @Override
    public WashersAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.washers_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WashersAdapter.MyViewHolder holder, int position) {
        String id = washerList.get(position).getId();
        String model = washerList.get(position).getModel();
        String program  = washerList.get(position).getProgram();
        String size = washerList.get(position).getSize();

        holder.id.setText(id);
        holder.model.setText(model);
        holder.program.setText(program);
        holder.size.setText(size);
    }

    @Override
    public int getItemCount() {
        return washerList.size();
    }
}
