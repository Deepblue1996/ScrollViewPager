package com.prohua.scrollviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prohua.scrollviewpager.adapter.MyCommonNavigatorAdapter;
import com.prohua.scrollviewpager.adapter.MyFragmentPagerAdapter;
import com.prohua.scrollviewpager.weight.CustomViewPager;
import com.prohua.scrollviewpager.weight.ScrollViewExtend;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;

/**
 * Demo ScrollView 嵌套> ViewPager 嵌套> RecyclerView
 * Created by Deep on 2018/2/2 0002.
 */
public class MainActivity extends AppCompatActivity {

    private CustomViewPager viewPager;
    private MagicIndicator magicIndicator;
    private MagicIndicator magicIndicator2;
    private TextView headerTextView;
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private ScrollViewExtend scrollViewExtend;

    private String[] titles = {"Tab One", "Tab Two", "Tab Three", "Tab Four"};

    private List<Integer> scrollPosition;

    private int nowPosition = 0;

    private int haveScrollY = 0;
    private int haveScrollYTemp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewId();
        initViewExt();

    }

    private void initViewId() {

        viewPager = (CustomViewPager) findViewById(R.id.viewPager);

        magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);

        magicIndicator2 = (MagicIndicator) findViewById(R.id.magic_indicator2);

        headerTextView = (TextView) findViewById(R.id.headerTextView);

        scrollViewExtend = (ScrollViewExtend) findViewById(R.id.scrollView);

        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
    }

    private void initViewExt() {

        viewPager.setOffscreenPageLimit(titles.length);
        viewPager.setAdapter(fragmentPagerAdapter);

        viewPager.addListenCurrentItem(new CustomViewPager.ListenCurrentItem() {
            @Override
            public void ListenCurrentItemMethod(int item) {
                // 记录前页
                haveScrollYTemp = haveScrollY;
            }
        });

        // ----------------- tabLayout

        magicIndicator.setBackgroundColor(Color.parseColor("#FFFFFF"));
        CommonNavigator commonNavigator = new CommonNavigator(getBaseContext());
        commonNavigator.setAdjustMode(true);

        commonNavigator.setAdapter(new MyCommonNavigatorAdapter(viewPager, titles));

        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(getBaseContext(), 15));
        ViewPagerHelper.bind(magicIndicator, viewPager);

        magicIndicator2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        CommonNavigator commonNavigator2 = new CommonNavigator(getBaseContext());
        commonNavigator2.setAdjustMode(true);

        commonNavigator2.setAdapter(new MyCommonNavigatorAdapter(viewPager, titles));
        magicIndicator2.setNavigator(commonNavigator2);
        LinearLayout titleContainer2 = commonNavigator2.getTitleContainer(); // must after setNavigator
        titleContainer2.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer2.setDividerPadding(UIUtil.dip2px(getBaseContext(), 15));
        ViewPagerHelper.bind(magicIndicator2, viewPager);

        //------------------- tabLayout

        scrollViewExtend.setOnScrollChangedListener(new ScrollViewExtend.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollViewExtend scrollViewExtend, int x, int y, int oldX, int oldY) {

                haveScrollY = y;

                if (y > ((LinearLayout.LayoutParams) headerTextView.getLayoutParams()).height) {
                    magicIndicator2.setVisibility(View.VISIBLE);
                } else {
                    magicIndicator2.setVisibility(View.GONE);
                }
            }
        });

        scrollPosition = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {
            scrollPosition.add(0);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                // 如果页码变了
                if (nowPosition != position) {
                    // 视图滑动延迟执行
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // execute the task
                            // anim scroll
                            // scrollViewExtend.smoothScrollTo(0, scrollPosition.get(position));
                            scrollViewExtend.scrollTo(0, scrollPosition.get(position));
                        }
                    }, 0);

                    scrollPosition.set(nowPosition, haveScrollYTemp);
                }
                nowPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 记录前页
                if (state == SCROLL_STATE_DRAGGING) {
                    haveScrollYTemp = haveScrollY;
                }
            }
        });

    }
}
