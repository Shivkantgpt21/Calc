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

public class GSTStructure extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gststructure, container, false);
        WebView gstWebView = (WebView) view.findViewById(R.id.gstWebView);
        WebView gstModelWebView = (WebView) view.findViewById(R.id.gstModelWebView);
        gstWebView.loadData("<html><body><p align=\"justify\">&nbsp;&nbsp;<b>Goods</b> and <b>Service Tax (GST)</b> is a comprehensive tax levy on manufacture, sale and consumption of goods and service at a national level under which no distinction is made between goods and services for levying of tax. It will mostly substitute all indirect taxes levied on goods and services by the Central and State Governments in India. </p></body></html>", "text/html", "utf-8");
        gstWebView.getSettings();
        gstWebView.setBackgroundColor(Color.TRANSPARENT);
        gstModelWebView.loadData("<html><body><ul type='square'><li>The GST will have <b>two components</b>: <br><b>1. Central GST or CGST</b>(levied by the Central Govt.)<br><b>2. State GST or SGST</b>(levied by the State Govt. )<br><br><b>Rates</b> for Central GST and State GST would be<b> approved appropriately</b>, reflecting revenue considerations and acceptability.</li><li>The CGST and the SGST would be <b>applicable to all transactions of goods and services</b> made for a consideration <b>except</b> the exempted goods and services.</li><li><b>Cross utilization of ITC both in case of Inputs and capital goods between the CGST and the SGST would not be permitted except in the case of inter-State supply of goods and services (i.e. IGST).</b></li><li>The Centre and the States would have <b>concurrent jurisdiction</b> for the entire value chain and for all taxpayers on the basis of thresholds for goods and services prescribed for the States and the Centre.</li></ul></body></html>", "text/html", "utf-8");
        gstModelWebView.getSettings();
        gstModelWebView.setBackgroundColor(Color.TRANSPARENT);
        return view;
    }

}
