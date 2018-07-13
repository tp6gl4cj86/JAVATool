package tw.com.tp6gl4cj86.java_tool.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by tp6gl4cj86 on 15/3/6.
 */
public class DisableSwipeViewPager extends ViewPager
{

    private boolean isEnableSwipe = false;

    public DisableSwipeViewPager(Context context)
    {
        super(context);
    }

    public DisableSwipeViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return false;
    }

}
