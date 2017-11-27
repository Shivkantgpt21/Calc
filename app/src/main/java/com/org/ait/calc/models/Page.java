package com.org.ait.calc.models;

import android.support.v4.app.Fragment;

/**
 * Created by Shiva on 26-10-2017.
 */

public class Page {
    private Fragment fragment;
    private String title;

    public Page(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getTitle() {
        return title;
    }
}
