package com.konifar.example.fabtransformation.adapters;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.Collection;
import java.util.List;

public abstract class BaseArrayAdapter<T> extends ArrayAdapter<T> {

    private int mLastPosition = -1;

    public BaseArrayAdapter(Context context, int resId, List<T> items) {
        super(context, resId, items);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addAll(Collection items) {
        if (items == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            super.addAll(items);
        } else {
            for (Object item : items) {
                super.add((T) item);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addAll(T... items) {
        if (items == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            super.addAll(items);
        } else {
            for (Object item : items) {
                super.add((T) item);
            }
        }
    }

    @Override
    public abstract View getView(int pos, View view, ViewGroup parent);

}
