package com.example.baked.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.baked.R;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_deatil);

        int position = getIntent().getIntExtra("POS", 0);
        setTitle(MasterListFragment.data.get(position).getName());

        Log.d("MY", "det created : " + position);

        Bundle bundle = new Bundle();
        bundle.putInt("POS", position);



        FragmentManager fragmentManager = getSupportFragmentManager();

        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(bundle);
        fragment.pos = position;

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_recipe_detail, fragment)
                .commit();
    }
}
