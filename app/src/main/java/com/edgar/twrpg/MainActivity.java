package com.edgar.twrpg;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "==========" + MainActivity.class.getName();
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private LinearLayout rootLayout;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.main_search_menu_item:
                FileProcUtil.shouldUpdate(this);
                Snackbar.make(rootLayout, "Test Start", Snackbar.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {

        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        if (getSupportActionBar() == null) {
            setSupportActionBar(mToolbar);
        }

        rootLayout = findViewById(R.id.main_root_layout);
        mViewPager = findViewById(R.id.main_view_pager);
        mTabLayout = findViewById(R.id.main_tab_layout);

        EquipmentsFragment equipmentsFragment = EquipmentsFragment.newInstance();
        UnitsFragment unitsFragment = UnitsFragment.newInstance(null, null);

        fragments.add(equipmentsFragment);
        fragments.add(unitsFragment);

        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initData() {

    }
}
