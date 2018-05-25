package tw.com.tp6gl4cj86.java_tool.Tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by tp6gl4cj86 on 2014/12/11.
 */
public class GlobalTimeTool
{
    private static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String timeZone = "GMT+8:00";

    /**
     * @param TimeZone ex : "GMT+8:00"
     */
    public static void setTimeZone(String TimeZone)
    {
        timeZone = TimeZone;
    }

    /**
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String getGlobalTime()
    {
        mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

        return mSimpleDateFormat.format(new Date());
    }

    public static Date getGlobalTimeDate()
    {
        mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

        Date date = null;
        try
        {
            date = mSimpleDateFormat.parse(mSimpleDateFormat.format(new Date()));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

}
