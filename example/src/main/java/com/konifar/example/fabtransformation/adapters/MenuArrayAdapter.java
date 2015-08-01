package com.konifar.example.fabtransformation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.konifar.example.fabtransformation.R;
import com.konifar.example.fabtransformation.models.SampleMenu;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MenuArrayAdapter extends BaseArrayAdapter<SampleMenu> {

    public MenuArrayAdapter(Context context) {
        super(context, R.layout.item_menu, new ArrayList<SampleMenu>());
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_menu, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        SampleMenu menu = getItem(pos);
        holder.imgThumb.setImageResource(menu.getDrawableResId());
        holder.txtTitle.setText(menu.getTitle());

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.img_thmub)
        ImageView imgThumb;
        @InjectView(R.id.txt_title)
        TextView txtTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
