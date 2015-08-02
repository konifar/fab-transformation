package com.konifar.example.fabtransformation;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.konifar.fab_transformation.FabTransformation;

import butterknife.InjectView;
import butterknife.OnClick;

public class TransformToSheetActivity extends BaseActivity {

    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.sheet)
    View sheet;
    @InjectView(R.id.overlay)
    View overlay;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, TransformToSheetActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_transform_to_sheet;
    }

    @Override
    protected void initView() {
        //
    }

    @OnClick(R.id.fab)
    void onClickFab() {
        if (fab.getVisibility() == View.VISIBLE) {
            FabTransformation.with(fab).setOverlay(overlay).transformIn(sheet);
        }
    }

    @OnClick(R.id.sheet)
    void onClickSheet() {
        if (fab.getVisibility() != View.VISIBLE) {
            FabTransformation.with(fab).setOverlay(overlay).transformOut(sheet);
        }
    }

    @OnClick(R.id.overlay)
    void onClickOverlay() {
        if (fab.getVisibility() != View.VISIBLE) {
            FabTransformation.with(fab).setOverlay(overlay).transformOut(sheet);
        }
    }

}
