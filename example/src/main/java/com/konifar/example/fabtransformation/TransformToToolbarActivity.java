package com.konifar.example.fabtransformation;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.konifar.fab_transformation.FabTransformation;

import butterknife.InjectView;
import butterknife.OnClick;

public class TransformToToolbarActivity extends BaseActivity {

    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.toolbar_footer)
    View toolbarFooter;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, TransformToToolbarActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_transform_to_toolbar;
    }

    @Override
    protected void initView() {
        //
    }

    @OnClick(R.id.fab)
    void onClickFab() {
        if (fab.getVisibility() == View.VISIBLE) {
            FabTransformation.transformIn(fab, toolbarFooter);
        }
    }

    @OnClick(R.id.toolbar_footer)
    void onClickToolbarFooter() {
        if (fab.getVisibility() != View.VISIBLE) {
            FabTransformation.transformOut(fab, toolbarFooter);
        }
    }
}
