package com.example.baked.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.baked.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        MasterListFragment listFragment = new MasterListFragment();

        // adding the fragments into the activity
        fragmentManager.beginTransaction()
                .add(R.id.fragment_master_list, listFragment)
                .commit();

    }
}
