package com.example.john.assignfour2017johnbukley;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by John on 30/04/2017.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;
    public static final int INFO = 0;
    public static final int MENU = 1;
    public static final int ORDER = 2;
    public static final int ACCOUNT = 3;
    public static final String INFO_TAB = "INFO";
    public static final String MENU_TAB = "MENU";
    public static final String ORDER_TAB = "ORDER";
    public static final String ACCOUNT_TAB = "ACCOUNT";

    public FragmentAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    public Fragment getItem(int position)
    {
        switch (position) {
            case INFO:
                InfoFragment infoTab = new InfoFragment();
                return infoTab;
            case MENU:
                MenuFragement menuTab = new MenuFragement();
                return menuTab;
            case ORDER:
                OrderFragment orderTab = new OrderFragment();
                return orderTab;
            case ACCOUNT:
                AccountFragment accountTab = new AccountFragment();
                return accountTab;
            default:
                return null;
        }
    }
    public int getCount()
    {
        return mNumOfTabs;
    }
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case INFO:
                return INFO_TAB;
            case MENU:
                return MENU_TAB;
            case ORDER:
                return ORDER_TAB;
            case ACCOUNT:
                return ACCOUNT_TAB;
            default:
                break;
        }
        return null;
    }
}
