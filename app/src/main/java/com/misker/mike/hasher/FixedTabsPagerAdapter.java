package com.misker.mike.hasher;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Mike on 3/3/17.
 * Supporting class for paginated main activity
 */

class FixedTabsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    FixedTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    protected void setContext(Context context){
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch(position) {
            case 1:
                return context.getResources().getString(R.string.TEXT);
            default:
                return context.getResources().getString(R.string.FILE);
        }
    }

}
