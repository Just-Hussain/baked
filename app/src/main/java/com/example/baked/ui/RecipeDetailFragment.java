package com.example.baked.ui;


import android.content.Intent;
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

public class RecipeDetailFragment extends Fragment implements DetailListAdapter.OnStepListener {

    int pos;
    List<Recipe> data;

    public RecipeDetailFragment() {
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
        Log.d("MY", "onStepClick: " + pos);

        final Intent intent = new Intent(getActivity(), StepDetailActivity.class);
        intent.putExtra("POS_STEP", pos);
        intent.putExtra("POS_RECIPE", this.pos);
        startActivity(intent);


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        testService testService = retrofit.create(RecipeDetailFragment.testService.class);
//
//        Call<List<Recipe>> call = testService.d();
//
//        call.enqueue(new Callback<List<Recipe>>() {
//            @Override
//            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
//                if (response.isSuccessful()) {
//                    Log.d("MTEST", "onResponse: well " + response);
//                }
//                else {
//                    Log.d("MTEST", "onResponse: well not " + response);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Recipe>> call, Throwable t) {
//                Log.d("MTEST", "onFailure:  " + t.getMessage());
//
//            }
//        });
    }

//    interface testService {
//        @GET("/topher/2017/May/59121517_baking/baking.json")
 //        Call<List<Recipe>> d();
//    }
}

