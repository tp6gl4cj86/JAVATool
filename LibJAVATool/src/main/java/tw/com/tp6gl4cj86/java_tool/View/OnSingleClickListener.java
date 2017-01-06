package tw.com.tp6gl4cj86.java_tool.View;

import android.os.SystemClock;
import android.view.View;

/**
 * Created by tp6gl4cj86 on 2015/5/28.
 */
public abstract class OnSingleClickListener implements View.OnClickListener
{

    private long mLastClickTime;

    public abstract void onSingleClick(View view);

    @Override
    public final void onClick(View view)
    {
        final long currentClickTime = SystemClock.uptimeMillis();
        final long elapsedTime = currentClickTime - mLastClickTime;
        mLastClickTime = currentClickTime;

        final long MIN_CLICK_INTERVAL = 600;
        if (elapsedTime <= MIN_CLICK_INTERVAL)
        {
            return;
        }

        onSingleClick(view);
    }

}