package com.example.baked.ui;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baked.R;
import com.example.baked.models.Step;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class StepDetailActivity extends AppCompatActivity {

    Step data;

    SimpleExoPlayer simpleExoPlayer;
    PlayerView pvVideo;
    TextView tvStepDesc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setp_detail);

        int posRecipe = getIntent().getIntExtra("POS_RECIPE", 0);
        int posStep = getIntent().getIntExtra("POS_STEP", 0);
        data = MasterListFragment.data.get(posRecipe).getSteps().get(posStep);

        setTitle(data.getShortDescription());

        pvVideo = findViewById(R.id.pv_video);
        tvStepDesc = findViewById(R.id.tv_step_desc);

        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        pvVideo.setPlayer(simpleExoPlayer);
        tvStepDesc.setText(data.getDescription());

        if (!data.getVideoURL().equals("")) {
            simpleExoPlayer.prepare(buildMediaSource(data.getVideoURL()));
            simpleExoPlayer.setPlayWhenReady(true);
        }
        else {
            pvVideo.setVisibility(View.GONE);
        }

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
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
        simpleExoPlayer = null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // TODO: player fills screen on rotation
    }
}
