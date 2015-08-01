package com.konifar.example.fabtransformation;

import android.content.Context;
import android.content.Intent;

public class TransformToToolbarActivity extends BaseActivity {

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
}
