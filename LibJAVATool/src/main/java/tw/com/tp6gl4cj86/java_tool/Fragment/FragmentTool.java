package tw.com.tp6gl4cj86.java_tool.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import tw.com.tp6gl4cj86.java_tool.Tool.JAVATool;

/**
 * Created by tp6gl4cj86 on 2016/3/15.
 */
public class FragmentTool
{

    public static <FM, F> void addFragmentAddToBackStack(FM fragmentManager, int layout, F fragment)
    {
        if (fragment != null)
        {
            addFragmentAddToBackStack(fragmentManager, layout, fragment, fragment.getClass()
                                                                                 .getName());
        }
    }

    public static <FM, F> void addFragmentAddToBackStack(FM fragmentManager, int layout, F fragment, String tag)
    {
        addFragmentAddToBackStack(fragmentManager, layout, fragment, tag, -1, -1, -1, -1);
    }

    public static <FM, F> void addFragmentAddToBackStack(FM fragmentManager, int layout, F fragment, int enter1, int exit1, int enter2, int exit2)
    {
        if (fragment != null)
        {
            addFragmentAddToBackStack(fragmentManager, layout, fragment, fragment.getClass()
                                                                                 .getName(), enter1, exit1, enter2, exit2);
        }
    }

    public static <FM, F> void addFragmentAddToBackStack(FM fragmentManager, int layout, F fragment, String tag, int enter1, int exit1, int enter2, int exit2)
    {
        addFragmentAddToBackStack(fragmentManager, layout, fragment, tag, enter1, exit1, enter2, exit2, true);
    }

    public static <FM, F> void addFragmentAddToBackStack(FM fragmentManager, int layout, F fragment, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag)
    {
        if (fragment != null)
        {
            addFragmentAddToBackStack(fragmentManager, layout, fragment, fragment.getClass()
                                                                                 .getName(), enter1, exit1, enter2, exit2, true);
        }
    }

    public static <FM, F> void addFragmentAddToBackStack(FM fragmentManager, int layout, F fragment, String tag, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag)
    {
        addFragment(fragmentManager, layout, fragment, tag, enter1, exit1, enter2, exit2, isCheckTag, true);
    }

    public static <FM, F> void addFragment(FM fragmentManager, int layout, F fragment)
    {
        addFragment(fragmentManager, layout, fragment, -1, -1, -1, -1);
    }

    public static <FM, F> void addFragment(FM fragmentManager, int layout, F fragment, int enter1, int exit1, int enter2, int exit2)
    {
        if (fragment != null)
        {
            addFragment(fragmentManager, layout, fragment, fragment.getClass()
                                                                   .getName(), enter1, exit1, enter2, exit2, false, false);
        }
    }

    public static <FM, F> void addFragment(FM fragmentManager, int layout, F fragment, String tag, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag, boolean isAddToBackStack)
    {
        if (fragmentManager != null && fragment != null)
        {
            if (JAVATool.isStringEmpty(tag))
            {
                tag = fragment.getClass()
                              .getName();
            }

            if (!isCheckTag || !isFragmentExistByTag(fragmentManager, tag))
            {
                if (fragmentManager instanceof FragmentManager)
                {
                    final FragmentTransaction fragmentTransaction = ((FragmentManager) fragmentManager).beginTransaction();
                    if (enter1 >= 0 && exit1 >= 0 && enter2 >= 0 && exit2 >= 0)
                    {
                        fragmentTransaction.setCustomAnimations(enter1, exit1, enter2, exit2);
                    }

                    fragmentTransaction.add(layout, ((Fragment) fragment), tag);
                    if (isAddToBackStack)
                    {
                        fragmentTransaction.addToBackStack(tag);
                    }

                    final Fragment lastFragment = getLastFragmentFromBackEntry(fragmentManager);
                    if (lastFragment != null)
                    {
                        fragmentTransaction.hide(lastFragment);
                    }

                    fragmentTransaction.commitAllowingStateLoss();
                }
                else if (fragmentManager instanceof android.support.v4.app.FragmentManager)
                {
                    final android.support.v4.app.FragmentTransaction fragmentTransaction = ((android.support.v4.app.FragmentManager) fragmentManager).beginTransaction();
                    if (enter1 >= 0 && exit1 >= 0 && enter2 >= 0 && exit2 >= 0)
                    {
                        fragmentTransaction.setCustomAnimations(enter1, exit1, enter2, exit2);
                    }

                    fragmentTransaction.add(layout, ((android.support.v4.app.Fragment) fragment), tag);
                    if (isAddToBackStack)
                    {
                        fragmentTransaction.addToBackStack(tag);
                    }

                    final android.support.v4.app.Fragment lastFragment = getLastFragmentFromBackEntry(fragmentManager);
                    if (lastFragment != null)
                    {
                        fragmentTransaction.hide(lastFragment);
                    }

                    fragmentTransaction.commitAllowingStateLoss();
                }
            }
        }
    }

    public static <FM> boolean isFragmentExistByTag(FM fragmentManager, String tag)
    {
        if (fragmentManager instanceof FragmentManager)
        {
            return ((FragmentManager) fragmentManager).findFragmentByTag(tag) != null;
        }
        else if (fragmentManager instanceof android.support.v4.app.FragmentManager)
        {
            return ((android.support.v4.app.FragmentManager) fragmentManager).findFragmentByTag(tag) != null;
        }
        return false;
    }

    public static <FM, F> F getLastFragmentFromBackEntry(FM fragmentManager)
    {
        final int position;

        if (fragmentManager instanceof FragmentManager)
        {
            position = ((FragmentManager) fragmentManager).getBackStackEntryCount() - 1;
            if (position >= 0)
            {
                final FragmentManager.BackStackEntry backStackEntry = ((FragmentManager) fragmentManager).getBackStackEntryAt(position);
                return (F) ((FragmentManager) fragmentManager).findFragmentByTag(backStackEntry.getName());
            }
        }
        else if (fragmentManager instanceof android.support.v4.app.FragmentManager)
        {
            position = ((android.support.v4.app.FragmentManager) fragmentManager).getBackStackEntryCount() - 1;
            if (position >= 0)
            {
                final android.support.v4.app.FragmentManager.BackStackEntry backStackEntry = ((android.support.v4.app.FragmentManager) fragmentManager).getBackStackEntryAt(position);
                return (F) ((android.support.v4.app.FragmentManager) fragmentManager).findFragmentByTag(backStackEntry.getName());
            }
        }

        return null;
    }

    public static void clearFragmentManagerBackStack(FragmentManager mFragmentManager)
    {
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
