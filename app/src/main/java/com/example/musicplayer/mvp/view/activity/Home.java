package com.example.musicplayer.mvp.view.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.musicplayer.R;
import com.example.musicplayer.mvp.view.adapter.HomeViewPagerAdapter;
import com.example.musicplayer.mvp.view.fragment.Account;
import com.example.musicplayer.mvp.view.fragment.Flame;
import com.example.musicplayer.mvp.view.fragment.Library;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public static ViewPager viewPager;
    List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initView();
        viewPager.setOffscreenPageLimit(3);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_flame:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.home_library:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.home_account:
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        viewPager.setOnTouchListener(new ViewPager.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (viewPager.getCurrentItem() == 3) {
                    return true;
                }
                return false;
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position <= 2) {
                    bottomNavigationView.getMenu().getItem(position).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    void initView() {
        bottomNavigationView = findViewById(R.id.home_navigation);
        viewPager = findViewById(R.id.home_viewPager);
        setPages();
        setPages();
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
    }

    private void setPages() {
        fragmentList = new ArrayList<>();
        Flame firstFragment = new Flame();
        fragmentList.add(firstFragment);
        Library secondFragment = new Library();
        fragmentList.add(secondFragment);
        Fragment thirdFragment = new Account();
        fragmentList.add(thirdFragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}