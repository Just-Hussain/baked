package com.example.baked.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baked.R;
import com.example.baked.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class MasterListAdapter extends RecyclerView.Adapter<MasterListAdapter.MasterListViewHolder> {

    private List<Recipe> data = new ArrayList<>();
    OnRecipeListener onRecipeListener;

    public MasterListAdapter() {

    }

    public MasterListAdapter(List<Recipe> data, OnRecipeListener onRecipeListener) {
        this.data = data;
        this.onRecipeListener = onRecipeListener;
    }

    @NonNull
    @Override
    public MasterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // specifies the .xml view to be used
        View view = inflater.inflate(R.layout.item_master_list, parent, false);

        // and pass it to the view holder to handle it
        MasterListViewHolder listViewHolder = new MasterListViewHolder(view);

        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MasterListViewHolder holder, int position) {
        // when a view holder gets bound, populate the view
        holder.bind(position);
    }

    // how many items are there
    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public void setData(List<Recipe> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    // the view holder represents one item in the recycler,
    // gets and populates the views in the item .xml
    public class MasterListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvRecipeName;

        public MasterListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRecipeName = itemView.findViewById(R.id.tv_recipe_name);

            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            tvRecipeName.setText(data.get(position).getName());
        }

        @Override
        public void onClick(View view) {
            onRecipeListener.onRecipeClick(getAdapterPosition());
        }
    }


    interface OnRecipeListener {
        void onRecipeClick(int pos);
    }
}
