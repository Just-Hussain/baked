package com.example.baked.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class RecipeDetailFragment extends Fragment implements DetailListAdapter.OnStepListener {

    int pos;
    List<Recipe> data;

    public RecipeDetailFragment() {
    }

    OnDataPass dataPasser;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detail_list, container, false);

        if (getArguments() != null) {
            pos = getArguments().getInt("POS_RECIPE");
        }

        data = MasterListFragment.data;

        // For the ingredient list recycler
        IngredientListAdapter ingredientListAdapter = new IngredientListAdapter();
        ingredientListAdapter.setData(data.get(pos).getIngredients());
        RecyclerView rvIngredientList = root.findViewById(R.id.rv_ingredient_list);
        rvIngredientList.setAdapter(ingredientListAdapter);
        rvIngredientList.setLayoutManager(new LinearLayoutManager(getActivity()));

        // For the step list recycler
        DetailListAdapter detailListAdapter = new DetailListAdapter(this);
        detailListAdapter.setData(data.get(pos).getSteps());
        RecyclerView rvDetailList = root.findViewById(R.id.rv_detail_list);
        rvDetailList.setAdapter(detailListAdapter);
        rvDetailList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    @Override
    public void onStepClick(int pos) {

        if (getActivity().findViewById(R.id.ll_detail_pane) != null) {
            dataPasser.onDataPass(pos, this.pos);
        }
        else {
            final Intent intent = new Intent(getActivity(), StepDetailActivity.class);
            intent.putExtra("POS_STEP", pos);
            intent.putExtra("POS_RECIPE", this.pos);
            startActivity(intent);
        }
    }


    public interface OnDataPass {
        void onDataPass(int posStep, int posRecipe);
    }

}

