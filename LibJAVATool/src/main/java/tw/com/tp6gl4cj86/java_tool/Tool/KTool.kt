package tw.com.tp6gl4cj86.java_tool.Tool

import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.lang.ref.WeakReference

class KTool {
    companion object {
        val gson by lazy { Gson() }
    }
}

fun <T> T.weakRef(): WeakReference<T> {
    return WeakReference(this)
}

inline fun <reified T> JSONArray.toObjList(clazz: Class<T> = T::class.java): ArrayList<T> {
    return ArrayList<T>().apply {
        for (i in 0 until length()) {
            add(optJSONObject(i).toObj(clazz))
        }
    }
}

inline fun <reified T> JSONObject.toObj(clazz: Class<T> = T::class.java): T {
    return KTool.gson.fromJson(toString(), clazz)
}