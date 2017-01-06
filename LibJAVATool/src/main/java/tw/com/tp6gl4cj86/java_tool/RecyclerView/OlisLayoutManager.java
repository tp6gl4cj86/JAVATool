package tw.com.tp6gl4cj86.java_tool.RecyclerView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tp6gl4cj86 on 15/4/30.
 */
public class OlisLayoutManager extends LinearLayoutManager
{

    public OlisLayoutManager(Context context)
    {
        super(context);
    }

    public OlisLayoutManager(Context context, int orientation, boolean reverseLayout)
    {
        super(context, orientation, reverseLayout);
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state)
    {
        return 300;
    }

}
