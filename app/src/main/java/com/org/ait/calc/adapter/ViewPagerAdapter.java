package com.org.ait.calc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.org.ait.calc.models.Page;

import java.util.List;

/**
 * Created by Shiva on 26-10-2017.
 */
//Adapter for the viewpager using FragmentPagerAdapter
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Page> mPageList;

    public ViewPagerAdapter(FragmentManager manager, List<Page> mPageList) {
        super(manager);
        this.mPageList = mPageList;
    }

    @Override
    public Fragment getItem(int position) {
        return mPageList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mPageList.size();
    }

    public void addFragment(Page page) {
        mPageList.add(page);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageList.get(position).getTitle();
    }
}
