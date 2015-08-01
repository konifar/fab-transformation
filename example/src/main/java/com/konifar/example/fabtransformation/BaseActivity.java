package com.konifar.example.fabtransformation;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;

abstract class BaseActivity extends AppCompatActivity {

    static final String EXTRA_TITLE = "extra_title";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        ActionBar bar = getSupportActionBar();
        if (title != null && bar != null) {
            bar.setTitle(title);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
            bar.setHomeButtonEnabled(true);
            toolbar.setTitle(title);
        }

        initView();

        overrideSlideEnterTransition();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void overrideSlideEnterTransition() {
        overridePendingTransition(R.anim.activity_slide_start_enter, R.anim.activity_scale_start_exit);
    }

    void overrideSlideExitTransition() {
        overridePendingTransition(R.anim.activity_scale_finish_enter, R.anim.activity_slide_finish_exit);
    }

    @Override
    public void finish() {
        super.finish();
        overrideSlideExitTransition();
    }

    abstract int getLayoutResId();

    protected abstract void initView();

}
