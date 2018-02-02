package com.prohua.scrollviewpager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

/**
 * Created by Deep on 2018/2/2 0002.
 */

public class MyCommonNavigatorAdapter extends CommonNavigatorAdapter {

    private ViewPager viewPager;

    private String[] titles;

    public MyCommonNavigatorAdapter(ViewPager viewPager, String[] titles) {
        this.viewPager = viewPager;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.length;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
        clipPagerTitleView.setText(titles[index]);
        clipPagerTitleView.setTextColor(Color.parseColor("#AAAAAA"));
        clipPagerTitleView.setClipColor(Color.parseColor("#3C3C3C"));
        clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(index);
            }
        });
        return clipPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
                /*LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#3C3C3C"));
                return indicator;*/
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setYOffset(UIUtil.dip2px(context, 3));
        indicator.setRoundRadius(UIUtil.dip2px(context, 2));
        indicator.setColors(Color.parseColor("#3C3C3C"));
        return indicator;
    }

}
