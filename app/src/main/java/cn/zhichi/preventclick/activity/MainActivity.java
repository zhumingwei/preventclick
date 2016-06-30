package cn.zhichi.preventclick.activity;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import cn.zhichi.preventclick.R;
import cn.zhichi.preventclick.adapter.AdapterMainFragmentPager;
import cn.zhichi.preventclick.common.MainTabFragEnum;
import cn.zhichi.preventclick.util.ViewUtils;
import cn.zhichi.preventclick.view.TabButtomLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager vpMain;
    private TabButtomLayout tabButtomLayout;
    private AdapterMainFragmentPager adapterMainFragmentPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
    }

    private void initView() {
        vpMain = (ViewPager)  findViewById(R.id.main_viewpager);
        vpMain.setOffscreenPageLimit(4);
        tabButtomLayout= (TabButtomLayout) findViewById(R.id.main_botton_tab_layout);
        tabButtomLayout.setViewPager(vpMain);
        adapterMainFragmentPager=new AdapterMainFragmentPager(MainActivity.this,getSupportFragmentManager(), MainTabFragEnum.values());
        vpMain.setAdapter(adapterMainFragmentPager);


    }
}
