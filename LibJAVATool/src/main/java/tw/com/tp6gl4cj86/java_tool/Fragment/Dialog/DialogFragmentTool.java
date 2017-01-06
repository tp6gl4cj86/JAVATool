package tw.com.tp6gl4cj86.java_tool.Fragment.Dialog;

import android.app.DialogFragment;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import tw.com.tp6gl4cj86.java_tool.R;

/**
 * Created by tp6gl4cj86 on 2017/1/6.
 */

public class DialogFragmentTool
{

    /*
     ######    ########   ##    ##   ##         ########
    ##    ##      ##       ##  ##    ##         ##
    ##            ##        ####     ##         ##
     ######       ##         ##      ##         ######
          ##      ##         ##      ##         ##
    ##    ##      ##         ##      ##         ##
     ######       ##         ##      ########   ########
    */

    /**
     * with status bar
     * onCreate
     */
    public static void setFullScreenDialogFragment(DialogFragment dialog)
    {
        if (dialog != null)
        {
            dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog);
        }
    }

    /**
     * without status bar
     * onCreate
     */
    public static void setFullWindowScreenDialogFragment(DialogFragment dialog)
    {
        if (dialog != null)
        {
            dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.FullWindowScreenDialog);
        }
    }


    /*
     ######    ########      ###     ########   ##     ##   ######    ########      ###     ########
    ##    ##      ##        ## ##       ##      ##     ##  ##    ##   ##     ##    ## ##    ##     ##
    ##            ##       ##   ##      ##      ##     ##  ##         ##     ##   ##   ##   ##     ##
     ######       ##      ##     ##     ##      ##     ##   ######    ########   ##     ##  ########
          ##      ##      #########     ##      ##     ##        ##   ##     ##  #########  ##   ##
    ##    ##      ##      ##     ##     ##      ##     ##  ##    ##   ##     ##  ##     ##  ##    ##
     ######       ##      ##     ##     ##       #######    ######    ########   ##     ##  ##     ##
    */

    /**
     * onCreateView
     */
    public static void setDialogFragmentStatusBarColor(DialogFragment dialog, int color)
    {
        setStatusBarColor(dialog.getDialog()
                                .getWindow(), color);
    }

    public static void setStatusBarColor(Window window, int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    /**
     * onCreateView
     */
    public static void setDialogFragmentBehindStatusBar(DialogFragment dialog)
    {
        setDialogFragmentBehindStatusBar(dialog, 0x00000000);
    }

    public static void setDialogFragmentBehindStatusBar(DialogFragment dialog, int color)
    {
        setBehindStatusBar(dialog.getDialog()
                                 .getWindow(), color);
    }

    private static void setBehindStatusBar(Window window, int color)
    {
        if (Build.VERSION.SDK_INT >= 16)
        {
            window.getDecorView()
                  .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            window.setStatusBarColor(color);
        }
        else if (Build.VERSION.SDK_INT >= 19)
        {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
