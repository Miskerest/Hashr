package com.misker.mike.hasher;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Mike on 3/3/17.
 * Supporting class for paginated main activity
 */

class FixedTabsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    FixedTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    void setContext(Context context){
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

        if (position == 1) {
            return context.getResources().getString(R.string.TEXT);
        }
        return context.getResources().getString(R.string.FILE);
    }

}
