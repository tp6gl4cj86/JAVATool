package tw.com.tp6gl4cj86.java_tool.RecyclerView;

import android.content.Context;
import android.graphics.PointF;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position)
    {
        final RecyclerView.SmoothScroller smoothScroller = new TopSnappedSmoothScroller(recyclerView.getContext());
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    private class TopSnappedSmoothScroller extends LinearSmoothScroller
    {
        TopSnappedSmoothScroller(Context context)
        {
            super(context);

        }

        @Override
        public PointF computeScrollVectorForPosition(int targetPosition)
        {
            return OlisLayoutManager.this.computeScrollVectorForPosition(targetPosition);
        }

        @Override
        protected int getVerticalSnapPreference()
        {
            return SNAP_TO_START;
        }
    }

}
