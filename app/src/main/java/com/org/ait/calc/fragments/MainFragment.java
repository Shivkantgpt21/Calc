package com.org.ait.calc.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.org.ait.calc.MainActivity;
import com.org.ait.calc.R;
import com.org.ait.calc.adapter.ViewPagerAdapter;
import com.org.ait.calc.base.BaseFragment;
import com.org.ait.calc.models.Page;

import java.util.ArrayList;

public class MainFragment extends BaseFragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null) {
            view = inflater.inflate(R.layout.content_main, container, false);
            ArrayList<Page> al = new ArrayList<>();
            al.add(new Page(new BasicCalculator(), getString(R.string.basic_calc)));
            al.add(new Page(new BasicCalculator(), getString(R.string.advanced_calc)));
            ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
            ViewPagerAdapter adapter = new ViewPagerAdapter(((AppCompatActivity) getContext()).getSupportFragmentManager(), al);
            viewPager.setAdapter(adapter);
            ((MainActivity)getActivity()).getTabLayout();
        }

        return  view;
    }

    }
