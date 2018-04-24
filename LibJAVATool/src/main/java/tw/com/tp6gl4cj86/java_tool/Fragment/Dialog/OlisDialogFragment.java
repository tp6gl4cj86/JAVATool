package tw.com.tp6gl4cj86.java_tool.Fragment.Dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

/**
 * Created by tp6gl4cj86 on 15/1/16.<p/>
 * setDialogListener();<br/>
 * super.onCreateView(inflater, container, savedInstanceState);<br/>
 * to do something...
 */
public class OlisDialogFragment extends DialogFragment
{

    public  Spring         mDialogSpring;
    private DialogListener mDialogListener;

    protected void setDialogListener(DialogListener mDialogListener)
    {
        this.mDialogListener = mDialogListener;
    }

    protected interface DialogListener
    {
        void initSpring();

        void onSpringUpdate(Spring spring);

        void onSpringEndStateChange(Spring spring);

        void onSpringAtRest(Spring spring);

        void onKeyBack();
    }

    public class DialogListenerAdapter implements DialogListener
    {

        @Override
        public void initSpring() {}

        @Override
        public void onSpringUpdate(Spring spring) {}

        @Override
        public void onSpringEndStateChange(Spring spring) {}

        @Override
        public void onSpringAtRest(Spring spring) {}

        @Override
        public void onKeyBack() {}
    }


    protected void setSpringConfig(double qcTension, double qcFriction)
    {
        mDialogSpring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(qcTension, qcFriction));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener()
        {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK && mDialogListener != null)
                {
                    mDialogListener.onKeyBack();
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });

        initSpring();
        if (isAutoPlay)
        {
            mDialogSpring.setEndValue(1);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    private boolean isDestroy = false;

    public boolean isAlive()
    {
        return isAdded() && !isDestroy;
    }

    @Override
    public void onDestroy()
    {
        isDestroy = true;
        if (mDialogSpring != null)
        {
            mDialogSpring.removeAllListeners();
        }
        super.onDestroy();
    }

    private boolean isAutoPlay = true;

    public void setIsAutoPlay(boolean isAutoPlay)
    {
        this.isAutoPlay = isAutoPlay;
    }

    private void initSpring()
    {
        if (mDialogListener != null)
        {
            mDialogListener.initSpring();
        }

        mDialogSpring = SpringSystem.create()
                                    .createSpring()
                                    .setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(50, 5))
                                    .addListener(new SimpleSpringListener()
                                    {
                                        @Override
                                        public void onSpringUpdate(Spring spring)
                                        {
                                            if (isAlive() && mDialogListener != null)
                                            {
                                                mDialogListener.onSpringUpdate(spring);
                                            }
                                        }

                                        @Override
                                        public void onSpringEndStateChange(Spring spring)
                                        {
                                            super.onSpringEndStateChange(spring);
                                            if (isAlive() && mDialogListener != null)
                                            {
                                                mDialogListener.onSpringEndStateChange(spring);
                                            }
                                        }

                                        @Override
                                        public void onSpringAtRest(Spring spring)
                                        {
                                            super.onSpringActivate(spring);
                                            if (isAlive())
                                            {
                                                if (mDialogListener != null)
                                                {
                                                    mDialogListener.onSpringAtRest(spring);
                                                }

                                                if (isDismiss(spring))
                                                {
                                                    new Handler().postDelayed(new Runnable()
                                                    {
                                                        @Override
                                                        public void run()
                                                        {
                                                            OlisDialogFragment.super.dismissAllowingStateLoss();
                                                        }
                                                    }, 10);
                                                }
                                            }
                                        }

                                    });
    }

    protected boolean isDismiss(Spring spring)
    {
        return spring.getEndValue() == 0;
    }


    public void show(Activity activity, String tag)
    {
        try
        {
            if (activity.getFragmentManager()
                        .findFragmentByTag(tag) == null)
            {
                show(activity.getFragmentManager()
                             .beginTransaction(), tag);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        dismissAllowingStateLoss();
    }

    @Override
    public void dismissAllowingStateLoss()
    {
        if (isAlive())
        {
            if (mDialogSpring != null && mDialogSpring.getEndValue() != 0)
            {
                mDialogSpring.setCurrentValue(mDialogSpring.getCurrentValue() + 0.01f);
                mDialogSpring.setOvershootClampingEnabled(true);
                mDialogSpring.setEndValue(0);
            }
            else
            {
                OlisDialogFragment.super.dismissAllowingStateLoss();
            }
        }
    }

    public void dismissImmediately()
    {
        if (isAlive())
        {
            OlisDialogFragment.super.dismissAllowingStateLoss();
        }
    }

}
