package com.org.ait.calc.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.org.ait.calc.R;

public class LinkFragment extends Fragment {
    private String fileName;
    private final static String TAG = "file";
    public static LinkFragment newInstance(String fileName) {
        LinkFragment f = new LinkFragment();
        Bundle args = new Bundle();
        args.putString(TAG, fileName);
        f.setArguments(args);
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout view = new LinearLayout(getActivity());
        fileName = getArguments().getString(TAG);
        WebView webView = new WebView(getActivity());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.loadUrl("file:///android_asset/"+fileName+".html");
        view.addView(webView);
        return view;
    }

}
