package com.example.baked.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.baked.R;

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_deatil);

        int position = getIntent().getIntExtra("POS_RECIPE", 0);
        setTitle(MasterListFragment.data.get(position).getName());

        Bundle bundle = new Bundle();
        bundle.putInt("POS_RECIPE", position);

        if (savedInstanceState == null) {
            RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_recipe_detail, fragment)
                    .commit();
        }
    }
}
