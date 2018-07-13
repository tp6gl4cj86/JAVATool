package tw.com.tp6gl4cj86.java_tool.Tool;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by tp6gl4cj86 on 2017/1/11.
 */

public class KeyboardTool
{

    public static void setKeyboardListener(final Context context, final View view, final KeyboardListener mKeyboardListener)
    {
        final int screenHeight = Math.max(context.getResources()
                                                 .getDisplayMetrics().widthPixels, context.getResources()
                                                                                          .getDisplayMetrics().heightPixels);

        view.getViewTreeObserver()
            .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
            {
                @Override
                public void onGlobalLayout()
                {
                    final Rect r = new Rect();
                    view.getWindowVisibleDisplayFrame(r);
                    final int keypadHeight = screenHeight - r.bottom;
                    if (keypadHeight > screenHeight * 0.15)
                    {
                        mKeyboardListener.OnOpen();
                    }
                    else
                    {
                        mKeyboardListener.OnClose();
                    }

                    mKeyboardListener.OnChange(keypadHeight);
                }
            });
    }

    public interface KeyboardListener
    {
        void OnOpen();

        void OnClose();

        void OnChange(int keypadHeight);
    }

    public static void hideKeyboard(Activity activity)
    {
        try
        {
            final View view = activity.getCurrentFocus();
            final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (view != null)
            {
                if (imm != null)
                {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void hideKeyboard(Activity activity, EditText mEditText)
    {
        try
        {
            final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
            {
                imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void showKeyboard(Activity activity)
    {
        try
        {
            getInputMethodManager(activity).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void showKeyboard(Activity activity, EditText mEditText)
    {
        if (mEditText.requestFocus())
        {
            try
            {
                getInputMethodManager(activity).showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private static InputMethodManager getInputMethodManager(Activity activity)
    {
        return (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}
