package cn.zhichi.preventclick.activity;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import cn.zhichi.preventclick.R;
import cn.zhichi.preventclick.adapter.AdapterMainFragmentPager;
import cn.zhichi.preventclick.common.MainTabFragEnum;
import cn.zhichi.preventclick.util.ViewUtils;
import cn.zhichi.preventclick.view.TabButtomLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager vpMain;
    private BottomNavigationBar tabButtomLayout;
    private AdapterMainFragmentPager adapterMainFragmentPager;
    private RelativeLayout topLayout1,topLayout2,topLayout3,topLayout4;
    private ImageView ivTop1,ivTop2,ivTop3,ivTop4;
    private TextView tvTop1,tvTop2,tvTop3,tvTop4;
    private ListView lvMainMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
        tabButtomLayout.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                vpMain.setCurrentItem(position,false);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void initView() {
        vpMain = (ViewPager)  findViewById(R.id.main_viewpager);
        vpMain.setOffscreenPageLimit(4);
        tabButtomLayout= (BottomNavigationBar) findViewById(R.id.main_botton_tab_layout);
        tabButtomLayout.setBarBackgroundColor(R.color.app_back_ground);
        tabButtomLayout.setActiveColor(R.color.tab_click_color);
        tabButtomLayout.setInActiveColor(R.color.tab_unclick_color);
        tabButtomLayout.setMode(BottomNavigationBar.MODE_FIXED);
        tabButtomLayout.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);


        tabButtomLayout
                .addItem(new BottomNavigationItem(R.drawable.main_page1_unclick, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.main_page2_unclick, "概况"))
                .addItem(new BottomNavigationItem(R.drawable.main_page3_unclick, "访客"))
                .addItem(new BottomNavigationItem(R.drawable.main_page4_unclick, "IP"))
                .addItem(new BottomNavigationItem(R.drawable.main_page5_unclick, "关键词"))
                .initialise();

        adapterMainFragmentPager=new AdapterMainFragmentPager(MainActivity.this,getSupportFragmentManager(), MainTabFragEnum.values());
        vpMain.setAdapter(adapterMainFragmentPager);
        lvMainMessage= (ListView) findViewById(R.id.main_fragment_listview);



    }
}
