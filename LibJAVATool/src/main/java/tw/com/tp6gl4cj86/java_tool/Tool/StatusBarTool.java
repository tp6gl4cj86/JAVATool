package tw.com.tp6gl4cj86.java_tool.Tool;

import android.app.Activity;
import android.app.DialogFragment;
import android.view.WindowManager;

/**
 * Created by tp6gl4cj86 on 2017/5/9.
 */

public class StatusBarTool
{
    public static void showStatusBar(Activity activity)
    {
        activity.getWindow()
                .clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void hideStatusBar(Activity activity)
    {
        activity.getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void showStatusBar(DialogFragment dialog)
    {
        if (dialog.getDialog()
                  .getWindow() != null)
        {
            dialog.getDialog()
                  .getWindow()
                  .clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public static void hideStatusBar(DialogFragment dialog)
    {
        if (dialog.getDialog()
                  .getWindow() != null)
        {
            dialog.getDialog()
                  .getWindow()
                  .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
}
