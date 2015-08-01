package com.konifar.example.fabtransformation;

import android.content.Context;
import android.content.Intent;

public class TransformToSheetActivity extends BaseActivity {

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

}
