package tw.com.tp6gl4cj86.java_tool.Tool;


import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;
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
    public static void setFullScreen(DialogFragment dialog)
    {
        setWindowStyle(dialog, R.style.FullScreenDialog);
    }

    /**
     * without status bar
     * onCreate
     */
    public static void setFullWindowScreen(DialogFragment dialog)
    {
        setWindowStyle(dialog, R.style.FullWindowScreenDialog);
    }

    private static void setWindowStyle(DialogFragment dialog, int style)
    {
        if (dialog != null)
        {
            dialog.setStyle(DialogFragment.STYLE_NORMAL, style);
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
    public static void setStatusBarColor(DialogFragment dialog, int color)
    {
        if (dialog != null && dialog.getDialog() != null)
        {
            setStatusBarColor(dialog.getDialog()
                                    .getWindow(), color);
        }
    }

    public static void setStatusBarColor(Window window, int color)
    {
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    /**
     * onCreateView
     */
    public static void setBehindStatusBar(DialogFragment dialog)
    {
        setBehindStatusBar(dialog, 0x00000000);
    }

    public static void setBehindStatusBar(DialogFragment dialog, int color)
    {
        if (dialog != null && dialog.getDialog() != null)
        {
            setBehindStatusBar(dialog.getDialog()
                                     .getWindow(), color);
        }
    }

    public static void setBehindStatusBar(Window window, int color)
    {
        if (window != null)
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


    /*
    ##    ##   ########   ##    ##   ########    #######      ###     ########   ########
    ##   ##    ##          ##  ##    ##     ##  ##     ##    ## ##    ##     ##  ##     ##
    ##  ##     ##           ####     ##     ##  ##     ##   ##   ##   ##     ##  ##     ##
    #####      ######        ##      ########   ##     ##  ##     ##  ########   ##     ##
    ##  ##     ##            ##      ##     ##  ##     ##  #########  ##   ##    ##     ##
    ##   ##    ##            ##      ##     ##  ##     ##  ##     ##  ##    ##   ##     ##
    ##    ##   ########      ##      ########    #######   ##     ##  ##     ##  ########
    */

    /**
     * onCreateView
     */
    public static void setKeyBoardResize(DialogFragment dialog)
    {
        setKeyBoardStyle(dialog, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    /**
     * onCreateView
     */
    public static void setKeyBoardPan(DialogFragment dialog)
    {
        setKeyBoardStyle(dialog, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private static void setKeyBoardStyle(DialogFragment dialog, int style)
    {
        if (dialog != null && dialog.getDialog() != null && dialog.getDialog()
                                                                  .getWindow() != null)
        {
            dialog.getDialog()
                  .getWindow()
                  .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
    }

}
