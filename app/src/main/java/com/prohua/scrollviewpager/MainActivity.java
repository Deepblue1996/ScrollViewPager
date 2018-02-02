package com.prohua.scrollviewpager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

/**
 * Created by Deep on 2018/2/2 0002.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.viewPager);

        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        final MagicIndicator magicIndicator2 = (MagicIndicator) findViewById(R.id.magic_indicator2);

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] titles = {"One", "Two", "Three", "Four"};//构造传递给fragment用于不同显示内容的参数

            /**
             * 根据id生成fragment，写好这个就好了
             * @param position
             * @return
             */
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = new MyFragment();
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
        };

        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(fragmentPagerAdapter);

        final String[] titles = {"Tab One", "Tab Two", "Tab Three", "Tab Four"};

        magicIndicator.setBackgroundColor(Color.parseColor("#FFFFFF"));
        CommonNavigator commonNavigator = new CommonNavigator(getBaseContext());
        commonNavigator.setAdjustMode(true);

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

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

        });

        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(getBaseContext(), 15));
        ViewPagerHelper.bind(magicIndicator, viewPager);

        magicIndicator2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        CommonNavigator commonNavigator2 = new CommonNavigator(getBaseContext());
        commonNavigator2.setAdjustMode(true);

        commonNavigator2.setAdapter(new CommonNavigatorAdapter() {

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

        });
        magicIndicator2.setNavigator(commonNavigator2);
        LinearLayout titleContainer2 = commonNavigator2.getTitleContainer(); // must after setNavigator
        titleContainer2.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer2.setDividerPadding(UIUtil.dip2px(getBaseContext(), 15));
        ViewPagerHelper.bind(magicIndicator2, viewPager);

        ScrollViewExtend scrollViewExtend = (ScrollViewExtend) findViewById(R.id.scrollView);
        scrollViewExtend.setOnScollChangedListener(new ScrollViewExtend.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollViewExtend scrollViewExtend, int x, int y, int oldX, int oldY) {
                Log.i("滑动","y:"+y+" oldY:"+oldY);
                if(y > UIUtil.dip2px(getBaseContext(), 200)) {
                    magicIndicator2.setVisibility(View.VISIBLE);
                } else {
                    magicIndicator2.setVisibility(View.GONE);
                }
            }
        });
    }

}
