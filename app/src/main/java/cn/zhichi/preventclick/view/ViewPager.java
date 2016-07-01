package cn.zhichi.preventclick.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.widget.Scroller;


import com.ashokvarma.bottomnavigation.utils.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.zhichi.preventclick.util.FixedSpeedScroller;
import cn.zhichi.preventclick.util.LogUtils;


/**
 * Created by ZMW on 2015/12/9.
 */
public class ViewPager extends android.support.v4.view.ViewPager {
    private boolean isFirstSet = true;

    public ViewPager(Context context) {
        super(context);
        initScoller();
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScoller();
    }

    private void initScoller() {
        try {
            Field field = android.support.v4.view.ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(this.getContext(),
                    new Interpolator() {
                        public float getInterpolation(float t) {
                            t -= 1.0f;
                            return t * t * t * t * t + 1.0f;
                        }
                    });
            field.set(this, scroller);
        } catch (Exception e) {
        }
    }


    private List<ViewGroup> layouts = new ArrayList<ViewGroup>();

    public void setChildLayout(ViewGroup layout) {
        layouts.add(layout);
    }

    public void clearChildLayout() {
        layouts.clear();
    }

    private List<WebView> webViews = new ArrayList<WebView>();

    public void setWebView(WebView webView) {
        webViews.add(webView);
    }

    public void clearWebViews() {
        webViews.clear();
    }

    float xDown;
    float yDown;
    float xMove;
    float yMove;
    boolean isVertical = false;


        @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isFirstSet=true;
                xDown = ev.getRawX();
                yDown = ev.getRawY();
                for (int i = 0; i < layouts.size(); i++) {
                    if (layouts.get(i) != null) {
                        layouts.get(i).setEnabled(true);
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                xMove = ev.getRawX();
                yMove = ev.getRawY();
                // 这里的动作判断是Viewpager滑动,ListView不滑动
                if (isFirstSet) {
                    isFirstSet = false;
                    if (Math.abs(yMove - yDown) >= Math.abs(xMove - xDown)) {
                        isVertical = true;
                    } else {
                        isVertical = false;
                    }
                }

                /**
                 * 判断当起始x坐标小于屏幕宽度的40/480，或大于屏幕宽度的(1.0 - 40 / 480.0)或起始y坐标大于（250 / 854.0 *
                 * specHeight）则，将事件向子控件传递
                 */
                //屏蔽网页的横滑
                if (!isVertical ) {
                    for (int i = 0; i < layouts.size(); i++) {
                        if (layouts.get(i) != null) {
                            LogUtils.debug("layouts enablefalse");
                            layouts.get(i).setEnabled(false);
                        }
                    }
//                    if(testcanScller(xDown,yDown)){
                        return true;
//                    }else{
//                        return false;
//                    }
//                    return super.onInterceptTouchEvent(ev);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                for (int i = 0; i < layouts.size(); i++) {
                    if (layouts.get(i) != null) {
                        layouts.get(i).setEnabled(true);
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof WebView) {
            return ((WebView) v).canScrollHorizontally(-dx);
        } else {
            return super.canScroll(v, checkV, dx, x, y);
        }
    }


    int specWidth;
    int specHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        specWidth = MeasureSpec.getSize(widthMeasureSpec);
        specHeight = MeasureSpec.getSize(heightMeasureSpec);
    }
}
