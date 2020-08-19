package tw.com.tp6gl4cj86.java_tool.Tool;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by tp6gl4cj86 on 2016/3/15.
 */
public class FragmentTool
{

    public static enum Method
    {
        ADD,
        REPLACE
    }

    private static boolean isHideLast = true;

    public static void enableHideLast()
    {
        isHideLast = true;
    }

    public static void disableHideLast()
    {
        isHideLast = false;
    }


    public static boolean addFragmentAddToBackStack(Method method, FragmentManager fragmentManager, int layout, Fragment fragment)
    {
        return addFragmentAddToBackStack(method, fragmentManager, layout, fragment, fragment.getClass()
                                                                                            .getName());
    }

    public static boolean addFragmentAddToBackStack(Method method, FragmentManager fragmentManager, int layout, Fragment fragment, String tag)
    {
        return addFragmentAddToBackStack(method, fragmentManager, layout, fragment, tag, -1, -1, -1, -1);
    }

    public static boolean addFragmentAddToBackStack(Method method, FragmentManager fragmentManager, int layout, Fragment fragment, int enter1, int exit1, int enter2, int exit2)
    {
        return addFragmentAddToBackStack(method, fragmentManager, layout, fragment, fragment.getClass()
                                                                                            .getName(), enter1, exit1, enter2, exit2);
    }

    public static boolean addFragmentAddToBackStack(Method method, FragmentManager fragmentManager, int layout, Fragment fragment, String tag, int enter1, int exit1, int enter2, int exit2)
    {
        return addFragmentAddToBackStack(method, fragmentManager, layout, fragment, tag, enter1, exit1, enter2, exit2, true);
    }

    public static boolean addFragmentAddToBackStack(Method method, FragmentManager fragmentManager, int layout, Fragment fragment, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag)
    {
        return addFragmentAddToBackStack(method, fragmentManager, layout, fragment, fragment.getClass()
                                                                                            .getName(), enter1, exit1, enter2, exit2, isCheckTag);
    }

    public static boolean addFragmentAddToBackStack(Method method, FragmentManager fragmentManager, int layout, Fragment fragment, String tag, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag)
    {
        return addFragment(method, fragmentManager, layout, fragment, tag, enter1, exit1, enter2, exit2, isCheckTag, true);
    }

    public static boolean addFragment(Method method, FragmentManager fragmentManager, int layout, Fragment fragment)
    {
        return addFragment(method, fragmentManager, layout, fragment, -1, -1, -1, -1);
    }

    public static boolean addFragment(Method method, FragmentManager fragmentManager, int layout, Fragment fragment, int enter1, int exit1, int enter2, int exit2)
    {
        return addFragment(method, fragmentManager, layout, fragment, fragment.getClass()
                                                                              .getName(), enter1, exit1, enter2, exit2, false, false);
    }

    public static boolean addFragment(Method method, FragmentManager fragmentManager, int layout, Fragment fragment, String tag, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag, boolean isAddToBackStack)
    {
        return addFragment(method, fragmentManager, layout, fragment, tag, null, enter1, exit1, enter2, exit2, isCheckTag, isAddToBackStack);
    }

    public static boolean addFragment(Method method, FragmentManager fragmentManager, int layout, Fragment fragment, String tag, Fragment lastFragment, int enter1, int exit1, int enter2, int exit2, boolean isCheckTag, boolean isAddToBackStack)
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
                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (enter1 >= 0 && exit1 >= 0 && enter2 >= 0 && exit2 >= 0)
                {
                    fragmentTransaction.setCustomAnimations(enter1, exit1, enter2, exit2);
                }

                switch (method)
                {
                    case ADD:
                        fragmentTransaction.add(layout, fragment, tag);
                        break;
                    case REPLACE:
                        fragmentTransaction.replace(layout, fragment, tag);
                        break;
                }
                if (isAddToBackStack)
                {
                    fragmentTransaction.addToBackStack(tag);
                }

                fragmentTransaction.commitAllowingStateLoss();

                if (lastFragment != null && isHideLast)
                {
                    final FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    fragmentTransaction2.hide(lastFragment);
                    fragmentTransaction2.commitAllowingStateLoss();
                }

                isSuccess = true;
            }
        }

        return isSuccess;
    }

    public static boolean isFragmentExistByTag(FragmentManager fragmentManager, String tag)
    {
        if (fragmentManager != null)
        {
            return fragmentManager.findFragmentByTag(tag) != null;
        }
        return false;
    }

    public static Fragment getLastFragmentFromBackEntry(FragmentManager fragmentManager)
    {
        final int position;

        if (fragmentManager != null)
        {
            position = fragmentManager.getBackStackEntryCount() - 1;
            if (position >= 0)
            {
                final FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(position);
                return fragmentManager.findFragmentByTag(backStackEntry.getName());
            }
        }

        return null;
    }

    public static void clearFragmentManagerBackStack(FragmentManager mFragmentManager)
    {
        if (mFragmentManager != null)
        {
            mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

}
