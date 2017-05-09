package tw.com.tp6gl4cj86.java_tool.Tool;

import android.app.Activity;
import android.content.pm.ActivityInfo;

/**
 * Created by tp6gl4cj86 on 2017/3/28.
 */

public class OrientationTool
{

    public static void setOrientationLeftLandscape(Activity activity)
    {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public static void setOrientationRightLandscape(Activity activity)
    {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
    }

    public static void setOrientationPortrait(Activity activity)
    {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void setOrientationAuto(Activity activity)
    {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
    }

}
