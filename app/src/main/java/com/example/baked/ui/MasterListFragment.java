package com.example.baked.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baked.R;
import com.example.baked.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class MasterListFragment extends Fragment implements MasterListAdapter.OnRecipeListener {

    String TAG = "MY";

//    IngredientListAdapter ingredientListAdapter = new IngredientListAdapter();
//    DetailListAdapter detailListAdapter = new DetailListAdapter();

    // Data retrieved from API
    public static List<Recipe> data = new ArrayList<>();


    MasterListAdapter listAdapter;

    // Must have empty constructor
    public MasterListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_master_list, container, false);

        // adapter setup
        listAdapter = new MasterListAdapter(data, this);

        // layout setup
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);

        // recycler setup
        RecyclerView recyclerView = root.findViewById(R.id.rv_master_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);

        // Async load data
        loadData();

        // returns full view to be displayed in hosting activity (Main)
        return root;
    }

    private void loadData() {
        // setup for retrofit, GSON handles parsing JSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.109:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create the required service
        RecipesService recipesService = retrofit.create(RecipesService.class);

        // create the call to be executed
        Call<List<Recipe>> call = recipesService.recipes();

        // async execute
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {
                    Log.d("resss", "onResponse: " + response.body().get(0).getName());

                    data = response.body();
                    listAdapter.setData(data);
                }
                else {
                    Log.d("resss", "onResponse: nah mate " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d("errr", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onRecipeClick(int pos) {
        Log.d(TAG, "onRecipeClick: " + pos);

        final Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
        intent.putExtra("POS", pos);
        startActivity(intent);
    }

    // Service interface provides abstract idea
    // for required HTTP requests to let Retrofit handles it.
    public interface RecipesService {
        @GET("/recipes")
        Call<List<Recipe>> recipes();
    }

}
