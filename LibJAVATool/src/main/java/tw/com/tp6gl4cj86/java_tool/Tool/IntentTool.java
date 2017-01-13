package tw.com.tp6gl4cj86.java_tool.Tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;

/**
 * Created by tp6gl4cj86 on 2017/1/13.
 */

public class IntentTool
{

    public static void intentToShare(Activity activity, String title, String url)
    {
        if (activity != null)
        {
            final Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, title);
            sendIntent.putExtra(Intent.EXTRA_TEXT, url);
            sendIntent.setType("text/plain");
            activity.startActivity(sendIntent);
        }
    }

    public static void intentToWeb(Activity activity, String Url)
    {
        if (activity != null)
        {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Url)));
        }
    }

    /**
     * <uses-permission android:name="android.permission.CALL_PHONE" />
     * <uses-feature android:name="android.hardware.telephony" android:required="false" />
     */
    public static boolean intentToCall(Activity activity, String Phone_Number)
    {
        if (activity != null && isCanCall(activity))
        {
            activity.startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + Phone_Number)));
            return true;
        }
        return false;
    }

    public static boolean intentToReadyCall(Activity activity, String Phone_Number)
    {
        try
        {
            if (activity != null && isCanCall(activity))
            {
                activity.startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + Phone_Number)));
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isCanCall(Activity activity)
    {
        return ((TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }

    public static void intentToSMS(Activity activity, String Phone_Number, String SMS)
    {
        if (activity != null)
        {
            activity.startActivity(new Intent(Intent.ACTION_SENDTO).setData(Uri.parse("smsto:" + Phone_Number))
                                                                   .putExtra("sms_body", SMS));
        }
    }

    public static void intentToMapByAddress(Activity activity, String address)
    {
        if (activity != null)
        {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address)));
        }
    }

    public static void intentToMarket(Activity activity, String packageName)
    {
        if (activity != null)
        {
            activity.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("market://details?id=" + packageName)));
        }
    }

    public static void intentToMarket(Activity activity)
    {
        if (activity != null)
        {
            intentToMarket(activity, activity.getPackageName());
        }
    }

    public static void intentToEmail(Activity activity, String email)
    {
        if (activity != null)
        {
            activity.startActivity(new Intent(Intent.ACTION_SENDTO).setData(Uri.parse("mailto:" + email)));
        }
    }

    public static void intentToEmail(Activity activity, String email, String title, String Content)
    {
        if (activity != null)
        {
            activity.startActivity(new Intent(Intent.ACTION_SENDTO).setData(Uri.parse("mailto:" + email))
                                                                   .putExtra(Intent.EXTRA_SUBJECT, title)
                                                                   .putExtra(Intent.EXTRA_TEXT, Content));
        }
    }

}
