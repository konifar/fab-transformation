package com.konifar.example.fabtransformation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.konifar.example.fabtransformation.adapters.MenuArrayAdapter;
import com.konifar.example.fabtransformation.models.SampleMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity {

    private static final String SPEC_URL = "http://www.google.com/design/spec/components/buttons-floating-action-button.html#";

    @InjectView(R.id.list_view)
    ListView listView;

    private MenuArrayAdapter adapter;

    private static void showWebPage(String url, Context context) {
        if (TextUtils.isEmpty(url)) return;

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    @Override
    int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        adapter = new MenuArrayAdapter(this);
        listView.setAdapter(adapter);
        adapter.addAll(createMenuList());
    }

    private List<SampleMenu> createMenuList() {
        List<SampleMenu> menuList = new ArrayList<>(2);
        menuList.add(new SampleMenu(getString(R.string.description_sheet), R.drawable.img_thumb_sheet));
        menuList.add(new SampleMenu(getString(R.string.description_toolbar), R.drawable.img_thumb_toolbar));
        menuList.add(new SampleMenu(getString(R.string.description_player), R.drawable.img_thumb_player));
        return menuList;
    }

    @OnItemClick(R.id.list_view)
    void onItemClickListView(int position) {
        SampleMenu sampleMenu = adapter.getItem(position);
        switch (position) {
            case 0:
                TransformToSheetActivity.start(this, sampleMenu.getTitle());
                break;
            case 1:
                TransformToToolbarActivity.start(this, sampleMenu.getTitle());
                break;
            case 2:
                TransformToPlayerActivity.start(this, sampleMenu.getTitle());
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_link:
                showWebPage(SPEC_URL, this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
