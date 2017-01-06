package tw.com.tp6gl4cj86.java_tool.Tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tp6gl4cj86 on 2017/1/6.
 */

public class JAVATool
{


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
