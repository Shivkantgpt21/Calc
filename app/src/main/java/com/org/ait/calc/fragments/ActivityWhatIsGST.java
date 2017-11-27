package com.org.ait.calc.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.org.ait.calc.R;

public class ActivityWhatIsGST extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gststructure, container, false);
        WebView gstWebView = (WebView) view.findViewById(R.id.gstWebView);
        gstWebView.getSettings().setJavaScriptEnabled(true);
        gstWebView.setBackgroundColor(Color.TRANSPARENT);
        gstWebView.loadData("<html><body ><p align=\"justify\">&nbsp;&nbsp;Goods and Service Tax (GST) is a comprehensive tax levy on manufacture, sale and consumption of goods and service at a national level under which no distinction is made between goods and services for levying of tax. It will mostly substitute all indirect taxes levied on goods and services by the Central and State Governments in India. </p></body></html>", "text/html", "utf-8");
        WebView whyGSTWebView = (WebView) view.findViewById(R.id.gstModelWebView);
        whyGSTWebView.loadData("<html><body><p align=\"justify\">&nbsp;&nbsp;One of the main objective of Goods Service Tax(GST) would be to eliminate the doubly taxation i.e. cascading effects of taxes on production and distribution cost of goods and services. The exclusion of cascading effects i.e. tax on tax till the level of final consumers will significantly improve the competitiveness of original goods and services in market which leads to beneficial impact to the GDP growth of the country. Introduction of a GST to replace the existing multiple tax structures of Centre and State taxes is not only desirable but imperative. Integration of various taxes into a GST system would make it possible to give full credit for inputs taxes collected. GST, being a destination-based consumption tax based on VAT principle. </p></body></html>", "text/html", "utf-8");
        whyGSTWebView.getSettings();
        whyGSTWebView.setBackgroundColor(Color.TRANSPARENT);
        //setTitle("What is GST?");
        return view;
    }

}
