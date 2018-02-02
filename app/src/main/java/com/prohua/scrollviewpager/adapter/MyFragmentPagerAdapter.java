package com.prohua.scrollviewpager.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.prohua.scrollviewpager.ChildFragment;

/**
 * Created by Deep on 2018/2/2 0002.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = {"One", "Two", "Three", "Four"};//构造传递给fragment用于不同显示内容的参数

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * 根据id生成fragment，写好这个就好了
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", titles[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 可以使已知数，也可以是一个集合的长度
     * @return
     */
    @Override
    public int getCount() {
        return titles.length;
    }
}
