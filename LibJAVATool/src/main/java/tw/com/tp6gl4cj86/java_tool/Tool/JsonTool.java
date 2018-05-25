package tw.com.tp6gl4cj86.java_tool.Tool;

import org.json.JSONObject;

/**
 * Created by tp6gl4cj86 on 2017/3/28.
 */

public class JsonTool
{

    public static boolean isJsonCode500(JSONObject json)
    {
        return isJsonCode(json, 500);
    }

    public static boolean isJsonCode495(JSONObject json)
    {
        return isJsonCode(json, 495);
    }

    public static boolean isJsonCode305(JSONObject json)
    {
        return isJsonCode(json, 305);
    }

    public static boolean isJsonCode(JSONObject json, int code)
    {
        return json != null && json.has("code") && json.optInt("code") == code;
    }

}
