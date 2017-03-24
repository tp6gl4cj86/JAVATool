package tw.com.tp6gl4cj86.java_tool.Tool;

import android.app.Activity;

/**
 * Created by tp6gl4cj86 on 2017/3/24.
 */

public class ActivityTool
{

    public static void setStatusBarColor(Activity activity, int color)
    {
        DialogFragmentTool.setStatusBarColor(activity.getWindow(), color);
    }

    public static void setBehindStatusBar(Activity activity)
    {
        setBehindStatusBar(activity, 0x00000000);
    }

    public static void setBehindStatusBar(Activity activity, int color)
    {
        DialogFragmentTool.setBehindStatusBar(activity.getWindow(), color);
    }

}
