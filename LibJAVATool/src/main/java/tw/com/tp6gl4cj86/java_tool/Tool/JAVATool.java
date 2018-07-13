package tw.com.tp6gl4cj86.java_tool.Tool;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

/**
 * Created by tp6gl4cj86 on 2017/1/6.
 */

public class JAVATool
{

    public static String getVersionName(Context context)
    {
        if (context != null)
        {
            try
            {
                return context.getPackageManager()
                              .getPackageInfo(context.getPackageName(), 0).versionName;
            }
            catch (PackageManager.NameNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        return "";
    }


    /*
     ######    ########   ########   ####  ##    ##    ######
    ##    ##      ##      ##     ##   ##   ###   ##   ##    ##
    ##            ##      ##     ##   ##   ####  ##   ##
     ######       ##      ########    ##   ## ## ##   ##   ####
          ##      ##      ##   ##     ##   ##  ####   ##    ##
    ##    ##      ##      ##    ##    ##   ##   ###   ##    ##
     ######       ##      ##     ##  ####  ##    ##    ######
    */
    public static boolean isStringEmpty(String string)
    {
        return !(string != null && !"".equals(string) && !"null".equals(string));
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static void copyText(Activity activity, String label, String text, String showHint)
    {
        final ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        final ClipData clip = ClipData.newPlainText(label, text);
        if (clipboard != null)
        {
            clipboard.setPrimaryClip(clip);
        }

        Toast.makeText(activity, showHint, Toast.LENGTH_SHORT)
             .show();
    }


    /*
    ########   ########    ######     #######   ########   ########
    ##     ##  ##         ##    ##   ##     ##  ##     ##  ##     ##
    ##     ##  ##         ##         ##     ##  ##     ##  ##     ##
    ########   ######     ##         ##     ##  ########   ##     ##
    ##   ##    ##         ##         ##     ##  ##   ##    ##     ##
    ##    ##   ##         ##    ##   ##     ##  ##    ##   ##     ##
    ##     ##  ########    ######     #######   ##     ##  ########
    */
    public static SharedPreferences getRecord(Context context)
    {
        return context.getSharedPreferences("record", Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getRecordEditor(Context context)
    {
        return getRecord(context).edit();
    }


}
