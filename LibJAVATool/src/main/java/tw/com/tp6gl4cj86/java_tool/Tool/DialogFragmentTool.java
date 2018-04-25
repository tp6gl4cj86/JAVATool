package tw.com.tp6gl4cj86.java_tool.Tool;


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
    public static <DF> void setFullScreen(DF dialog)
    {
        setWindowStyle(dialog, R.style.FullScreenDialog);
    }

    /**
     * without status bar
     * onCreate
     */
    public static <DF> void setFullWindowScreen(DF dialog)
    {
        setWindowStyle(dialog, R.style.FullWindowScreenDialog);
    }

    private static <DF> void setWindowStyle(DF dialog, int style)
    {
        if (dialog != null)
        {
            if (dialog instanceof DialogFragment)
            {
                ((DialogFragment) dialog).setStyle(DialogFragment.STYLE_NORMAL, style);
            }
            else if (dialog instanceof android.support.v4.app.DialogFragment)
            {
                ((android.support.v4.app.DialogFragment) dialog).setStyle(android.support.v4.app.DialogFragment.STYLE_NORMAL, style);
            }
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
    public static <DF> void setStatusBarColor(DF dialog, int color)
    {
        if (dialog != null)
        {
            if (dialog instanceof DialogFragment)
            {
                setStatusBarColor(((DialogFragment) dialog).getDialog()
                                                           .getWindow(), color);
            }
            else
            {
                setStatusBarColor(((android.support.v4.app.DialogFragment) dialog).getDialog()
                                                                                  .getWindow(), color);
            }
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
    public static <DF> void setBehindStatusBar(DF dialog)
    {
        setBehindStatusBar(dialog, 0x00000000);
    }

    public static <DF> void setBehindStatusBar(DF dialog, int color)
    {
        if (dialog != null)
        {
            if (dialog instanceof DialogFragment)
            {
                setBehindStatusBar(((DialogFragment) dialog).getDialog()
                                                            .getWindow(), color);
            }
            else if (dialog instanceof android.support.v4.app.DialogFragment)
            {
                setBehindStatusBar(((android.support.v4.app.DialogFragment) dialog).getDialog()
                                                                                   .getWindow(), color);
            }
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
    public static <DF> void setKeyBoardResize(DF dialog)
    {
        setKeyBoardStyle(dialog, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    /**
     * onCreateView
     */
    public static <DF> void setKeyBoardPan(DF dialog)
    {
        setKeyBoardStyle(dialog, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private static <DF> void setKeyBoardStyle(DF dialog, int style)
    {
        if (dialog != null)
        {
            if (dialog instanceof DialogFragment)
            {
                ((DialogFragment) dialog).getDialog()
                                         .getWindow()
                                         .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            }
            else if (dialog instanceof android.support.v4.app.DialogFragment)
            {
                ((android.support.v4.app.DialogFragment) dialog).getDialog()
                                                                .getWindow()
                                                                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            }
        }
    }

}
