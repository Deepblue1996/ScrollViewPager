package com.prohua.scrollviewpager.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * 自适应ViewPager
 * Created by Deep on 2018/2/2 0002.
 */

public class CustomViewPager extends ViewPager {

    private static final String TAG = "CustomViewPager";

    private int position = 0;

    public CustomViewPager(Context context) {
        this(context, null);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                CustomViewPager.this.position = position;
                requestLayout();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        boolean have = false;

        if (getChildAt(position) instanceof ViewGroup) {
            ViewGroup childView = (ViewGroup) getChildAt(position);
            for (int j = 0; j < childView.getChildCount(); j++) {
                View childChildView = childView.getChildAt(j);
                if (childChildView instanceof ScrollView) {
                    have = true;
                    Log.i(TAG, "ViewGroup have ScrollView");
                }
            }
        }

        // ScrollView
        if (have) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Log.d(TAG, "onMeasure");
            // find the current child view
            View view = getChildAt(getCurrentItem());
            if (view != null) {
                // measure the current child view with the specified measure spec
                view.measure(widthMeasureSpec, heightMeasureSpec);
            }

            setMeasuredDimension(getMeasuredWidth(), measureHeight(heightMeasureSpec, view));
        } else {
            int height = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.measure(widthMeasureSpec,
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                int h = child.getMeasuredHeight();
                if (h > height)
                    height = h;
                Log.i(TAG, "MeasuredHeight:" + height);
            }
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                    MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @param view        the base view with already measured height
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec, View view) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            // set the height from the base view if available
            if (view != null) {
                result = view.getMeasuredHeight();
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private ListenCurrentItem listenCurrentItem;

    public void addListenCurrentItem(ListenCurrentItem listenCurrentItem) {
        this.listenCurrentItem = listenCurrentItem;
    }

    @Override
    public void setCurrentItem(int item) {
        listenCurrentItem.ListenCurrentItemMethod(item);
        super.setCurrentItem(item);
    }

    public interface ListenCurrentItem {
        void ListenCurrentItemMethod(int item);
    }
}