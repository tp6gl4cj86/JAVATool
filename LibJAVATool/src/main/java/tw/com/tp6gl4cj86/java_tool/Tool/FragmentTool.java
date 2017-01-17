package tw.com.tp6gl4cj86.java_tool.Tool;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by tp6gl4cj86 on 2016/3/15.
 */
public class FragmentTool
{

    public static enum Method
    {
        ADD, REPLACE
    }

    public static <FM, F> boolean addFragmentAddToBackStack(Method method, FM fragmentManager, int layout, F fragment)
    {
        return addFragmentAddToBackStack(method, fragmentManager, layout, fragment, fragment.getClass()
                                                                                            .getName());
    }

    public static <FM, F> boolean addFragmentAddToBackStack(Method method, FM fragmentManager, int layout, F fragment, String tag)
    {
        return addFragmentAddToBackStack(method, fragmentManager, layout, fragment, tag, -1, -1, -1, -1);
    }

    public static <FM, F> boolean addFragmentAddToBackStack(Method method, FM fragmentManager, int layout, F fragment, int enter1, int exit1, int enter2, int exit2)
    {
        return addFragmentAddToBackStack(method, fragmentManager, layout, fragment, fragment.getClass()
                                                                                            .getName(), enter1, exit1, enter2, exit2);
    }

    public static <FM, F> boolean addFragmentAddToBackStack(Method method, FM fragmentManager, int layout, F fragment, String tag, int enter1, int exit1, int enter2, int exit2)
    {
        return addFragmentAddToBackStack(method, fragmentManager, layout, fragment, tag, enter1, exit1, enter2, exit2, true);
    }

    public static <FM, F> boolean addFragmentAddToBackStack(Method method, FM fragmentManager, int layout, F fragment, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag)
    {
        return addFragmentAddToBackStack(method, fragmentManager, layout, fragment, fragment.getClass()
                                                                                     .getName(), enter1, exit1, enter2, exit2, true);
    }

    public static <FM, F> boolean addFragmentAddToBackStack(Method method, FM fragmentManager, int layout, F fragment, String tag, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag)
    {
        return addFragment(method, fragmentManager, layout, fragment, tag, enter1, exit1, enter2, exit2, isCheckTag, true);
    }

    public static <FM, F> boolean addFragment(Method method, FM fragmentManager, int layout, F fragment)
    {
        return addFragment(method, fragmentManager, layout, fragment, -1, -1, -1, -1);
    }

    public static <FM, F> boolean addFragment(Method method, FM fragmentManager, int layout, F fragment, int enter1, int exit1, int enter2, int exit2)
    {
        return addFragment(method, fragmentManager, layout, fragment, fragment.getClass()
                                                                       .getName(), enter1, exit1, enter2, exit2, false, false);
    }

    public static <FM, F> boolean addFragment(Method method, FM fragmentManager, int layout, F fragment, String tag, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag, boolean isAddToBackStack)
    {
        return addFragment(method, fragmentManager, layout, fragment, tag, null, enter1, exit1, enter2, exit2, isCheckTag, isAddToBackStack);
    }

    public static <FM, F> boolean addFragment(Method method, FM fragmentManager, int layout, F fragment, String tag, F lastFragment, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag, boolean isAddToBackStack)
    {
        boolean isSuccess = false;

        if (fragmentManager != null && fragment != null)
        {
            if (JAVATool.isStringEmpty(tag))
            {
                tag = fragment.getClass()
                              .getName();
            }

            if (lastFragment == null)
            {
                lastFragment = getLastFragmentFromBackEntry(fragmentManager);
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

                    switch (method)
                    {
                        case ADD:
                            fragmentTransaction.add(layout, ((Fragment) fragment), tag);
                            break;
                        case REPLACE:
                            fragmentTransaction.replace(layout, ((Fragment) fragment), tag);
                            break;
                    }
                    if (isAddToBackStack)
                    {
                        fragmentTransaction.addToBackStack(tag);
                    }

                    if (lastFragment != null)
                    {
                        fragmentTransaction.hide((Fragment) lastFragment);
                    }

                    fragmentTransaction.commitAllowingStateLoss();
                    isSuccess = true;
                }
                else if (fragmentManager instanceof android.support.v4.app.FragmentManager)
                {
                    final android.support.v4.app.FragmentTransaction fragmentTransaction = ((android.support.v4.app.FragmentManager) fragmentManager).beginTransaction();
                    if (enter1 >= 0 && exit1 >= 0 && enter2 >= 0 && exit2 >= 0)
                    {
                        fragmentTransaction.setCustomAnimations(enter1, exit1, enter2, exit2);
                    }

                    switch (method)
                    {
                        case ADD:
                            fragmentTransaction.add(layout, ((android.support.v4.app.Fragment) fragment), tag);
                            break;
                        case REPLACE:
                            fragmentTransaction.replace(layout, ((android.support.v4.app.Fragment) fragment), tag);
                            break;
                    }
                    if (isAddToBackStack)
                    {
                        fragmentTransaction.addToBackStack(tag);
                    }

                    if (lastFragment != null)
                    {
                        fragmentTransaction.hide((android.support.v4.app.Fragment) lastFragment);
                    }

                    fragmentTransaction.commitAllowingStateLoss();
                    isSuccess = true;
                }
            }
        }

        return isSuccess;
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
