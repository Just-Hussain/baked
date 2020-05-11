package com.example.baked.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baked.R;

public class StepDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setp_detail);

        int posRecipe = getIntent().getIntExtra("POS_RECIPE", 0);
        int posStep = getIntent().getIntExtra("POS_STEP", 0);
        setTitle(MasterListFragment.data.get(posRecipe).getSteps().get(posStep).getShortDescription());
    }
}
