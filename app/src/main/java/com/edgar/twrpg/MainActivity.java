package com.edgar.twrpg;

import android.os.Bundle;
import android.util.Log;
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
//                String testString = "Description=|c0040e0d0[药水]|r |n|CFFFFDEAD一种神奇的混合草药和治疗药水制作的|r |n|c00adff2f∴|r|c0080ff0020秒持续时间 生命值恢复 +800|r";
//                String outString = FileProcUtil.removeColorCodes(testString);
//                outString = FileProcUtil.removeNewLinesAndQuotes(outString);
//                String testString = "Art=ReplaceableTextures\\CommandButtons\\BTNGemSapphire.blp";
//                String outString = FileProcUtil.getIconFilePath(testString);
//                String testString = "Name=\"|c0052E252Benedict, the Crimson Armor of Blood|r\"";
//                String outString = FileProcUtil.getItemName(testString);
                String testString = "Description=|c00FF8200[罕见]|n|c002040f0∴|c00ff8c00攻击力+1200|n|c002040f0∴|c00ff8c00全属性 +90|n|c002040f0∴|c0040e0d0移动速度 +200|n|c00adff2f▣ |c00ffff00等级.120";
//                String outString = FileProcUtil.getLevelString(testString);
                String outString = FileProcUtil.getItemQuality(testString);
                Log.d(TAG, "onOptionsItemSelected: outString: " + outString);
                FileProcUtil.checkUpdate(this);
                Snackbar.make(rootLayout, outString, Snackbar.LENGTH_SHORT).show();
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

        EquipmentsFragment equipmentsFragment = EquipmentsFragment.newInstance(null, null);
        UnitsFragment unitsFragment = UnitsFragment.newInstance(null, null);

        fragments.add(equipmentsFragment);
        fragments.add(unitsFragment);

        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }
}
