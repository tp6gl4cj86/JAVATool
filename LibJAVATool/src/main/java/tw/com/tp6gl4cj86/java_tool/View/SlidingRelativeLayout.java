package tw.com.tp6gl4cj86.java_tool.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

/**
 * Created by tp6gl4cj86 on 15/3/18.
 */
public class SlidingRelativeLayout extends RelativeLayout
{


    public SlidingRelativeLayout(Context context)
    {
        super(context);
    }

    public SlidingRelativeLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SlidingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }


    private ViewTreeObserver.OnPreDrawListener preDrawListener = null;
    private float                              yFraction       = 0;

    public void setYFraction(final float fraction)
    {
        this.yFraction = fraction;

        if (getHeight() == 0)
        {
            if (preDrawListener == null)
            {
                preDrawListener = new ViewTreeObserver.OnPreDrawListener()
                {
                    @Override
                    public boolean onPreDraw()
                    {
                        getViewTreeObserver().removeOnPreDrawListener(preDrawListener);
                        setYFraction(yFraction);
                        return true;
                    }
                };
                getViewTreeObserver().addOnPreDrawListener(preDrawListener);
            }
            return;
        }

        setTranslationY(getHeight() * fraction);
    }

    public float getYFraction()
    {
        if (getHeight() == 0)
        {
            return 0;
        }
        return getTranslationY() / getHeight();
    }


    private float xFraction = 0;

    public void setXFraction(final float fraction)
    {
        this.xFraction = fraction;

        if (getWidth() == 0)
        {
            if (preDrawListener == null)
            {
                preDrawListener = new ViewTreeObserver.OnPreDrawListener()
                {
                    @Override
                    public boolean onPreDraw()
                    {
                        getViewTreeObserver().removeOnPreDrawListener(preDrawListener);
                        setXFraction(xFraction);
                        return true;
                    }
                };
                getViewTreeObserver().addOnPreDrawListener(preDrawListener);
            }
            return;
        }

        setTranslationX(getWidth() * fraction);
    }

    public float getXFraction()
    {
        if (getWidth() == 0)
        {
            return 0;
        }
        return getTranslationX() / getWidth();
    }

}
