package com.example.baked.ui;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.baked.R;
import com.example.baked.RecipeWidgetProvider;
import com.example.baked.models.Step;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnDataPass {

    Step data;
    SimpleExoPlayer simpleExoPlayer;
    int position;
    public boolean twoPane;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_deatil);

        position = getIntent().getIntExtra("POS_RECIPE", 0);
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


        twoPane = findViewById(R.id.ll_detail_pane) != null;

        Log.d("PANE", "onCreate: of recipe detail activity, twopane ?: " + twoPane);
    }


    private MediaSource buildMediaSource(String url) {
        Uri uri = Uri.parse(url);
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "baked");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
            Log.d("PANE", "onDestroy: killed player");

        }
    }

    protected void onPause() {
        super.onPause();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
            Log.d("PANE", "onDestroy: killed player");

        }
    }

    @Override
    public void onDataPass(int posStep, int posRecipe) {
        PlayerView pvVideo;
        TextView tvStepDesc;

        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
            Log.d("PANE", "onDataPass: killed player");
        }

        data = MasterListFragment.data.get(posRecipe).getSteps().get(posStep);

        setTitle(data.getShortDescription());

        pvVideo = findViewById(R.id.pv_video);
        tvStepDesc = findViewById(R.id.tv_step_desc);

        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        pvVideo.setPlayer(simpleExoPlayer);
        tvStepDesc.setText(data.getDescription());

        if (!data.getVideoURL().equals("")) {
            pvVideo.setVisibility(View.VISIBLE);
            simpleExoPlayer.prepare(buildMediaSource(data.getVideoURL()));
            simpleExoPlayer.setPlayWhenReady(true);
        }
        else {
            pvVideo.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipe_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_set_widget) {
            RecipeWidgetProvider.setData(MasterListFragment.data.get(position));
        }

        return super.onOptionsItemSelected(item);
    }
}
