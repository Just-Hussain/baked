package com.example.baked.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baked.R;
import com.example.baked.models.Recipe;

import java.util.List;

public class RecipeDetailFragment extends Fragment {

    int pos;
    List<Recipe> data;

    public RecipeDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // TODO: pos should be passed from main activity, problem is getArguments is null initially


        data = MasterListFragment.data;

        IngredientListAdapter ingredientListAdapter = new IngredientListAdapter();
        DetailListAdapter detailListAdapter = new DetailListAdapter();

        if (getArguments() != null) {
            pos = getArguments().getInt("POS", 0);
            detailListAdapter.setData(data.get(pos).getSteps());
            ingredientListAdapter.setData(data.get(pos).getIngredients());
        }


        Log.d("bundlemy", "onCreateView: " + getArguments());



        View root = inflater.inflate(R.layout.fragment_detail_list, container, false);



        Log.d("MY", "bet frag:  "  + pos);



        // For the ingredient list recycler
        ingredientListAdapter.setData(data.get(pos).getIngredients());
        RecyclerView rvIngredientList = root.findViewById(R.id.rv_ingredient_list);
        rvIngredientList.setAdapter(ingredientListAdapter);
        rvIngredientList.setLayoutManager(new LinearLayoutManager(getActivity()));

        // For the step list recycler
        detailListAdapter.setData(data.get(pos).getSteps());
        RecyclerView rvDetailList = root.findViewById(R.id.rv_detail_list);
        rvDetailList.setAdapter(detailListAdapter);
        rvDetailList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }
}
