package com.example.baked.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baked.R;
import com.example.baked.models.Step;

import java.util.List;

public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.DetailListViewHolder> {

    private List<Step> data;

    public void setData(List<Step> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        Log.d( "MY", "onCreateViewHolder: detail adapter");
        View view = inflater.inflate(R.layout.item_detail_list, parent, false);

        DetailListViewHolder viewHolder = new DetailListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public class DetailListViewHolder extends RecyclerView.ViewHolder {

        TextView tvStepShortDesc;

        public DetailListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStepShortDesc = itemView.findViewById(R.id.tv_step_short_desc);
        }

        public void bind(int position) {
            Log.d("MY", "bind step: bindin steps");
            tvStepShortDesc.setText(data.get(position).getShortDescription());
        }
    }
}
