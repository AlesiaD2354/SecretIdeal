package com.example.fooddiary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddiary.R;

import java.util.ArrayList;

public class UserWeightAdapter extends RecyclerView.Adapter<UserWeightAdapter.UserWeightHolder> {

    Context context;

    ArrayList<String> year = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> weight = new ArrayList<String>();

    public UserWeightAdapter(Context context, ArrayList<String> year, ArrayList<String> date, ArrayList<String> weight) {
        this.context = context;
        this.year = year;
        this.date = date;
        this.weight = weight;
    }

    @NonNull
    @Override
    public UserWeightAdapter.UserWeightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_weight_item, parent, false);
        return new UserWeightHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserWeightAdapter.UserWeightHolder holder, int position) {
        if (year.get(position) == null) {
            holder.year.setVisibility(View.GONE);
        } else if(year.size() == 0 || year.isEmpty()) {
            Toast.makeText(context, "Nothing to display", Toast.LENGTH_SHORT).show();
        } else {
            holder.year.setText(year.get(position));
        }
        holder.date.setText(date.get(position));
        holder.weight.setText(weight.get(position));
    }
    @Override
    public int getItemCount() {
        return date.size();
    }

    public class UserWeightHolder extends RecyclerView.ViewHolder {
        public TextView year, date, weight;

        public UserWeightHolder(@NonNull View itemView) {
            super(itemView);
            year = itemView.findViewById(R.id.year);
            date = itemView.findViewById(R.id.date);
            weight = itemView.findViewById(R.id.weight);
        }
    }
}
