package com.example.baked.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baked.R;
import com.example.baked.models.Ingredient;

import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientListViewHolder> {

    private List<Ingredient> data;

    public void setData(List<Ingredient> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_ingredient_list, parent, false);

        IngredientListViewHolder viewHolder = new IngredientListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public class IngredientListViewHolder extends RecyclerView.ViewHolder {

        TextView tvIngredientName;
        TextView tvIngredientMeasure;
        TextView tvIngredientQuantity;

        public IngredientListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIngredientName = itemView.findViewById(R.id.tv_ingredient_name);
            tvIngredientMeasure = itemView.findViewById(R.id.tv_ingredient_measure);
            tvIngredientQuantity = itemView.findViewById(R.id.tv_ingredient_quantity);

        }

        public void bind(int position) {
            Log.d("MY", "bind ing: bindin ings");
            tvIngredientName.setText(data.get(position).getIngredient());
            tvIngredientMeasure.setText(data.get(position).getMeasure());
            tvIngredientQuantity.setText(data.get(position).getQuantity() + "");
        }
    }
}
