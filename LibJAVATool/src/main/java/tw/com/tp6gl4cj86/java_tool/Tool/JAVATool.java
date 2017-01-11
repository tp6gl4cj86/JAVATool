package tw.com.tp6gl4cj86.java_tool.Tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

/**
 * Created by tp6gl4cj86 on 2017/1/6.
 */

public class JAVATool
{

    public static String getVersionName(Context context)
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
