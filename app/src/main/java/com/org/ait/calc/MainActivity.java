package com.org.ait.calc;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.org.ait.calc.fragments.ActivityWhatIsGST;
import com.org.ait.calc.fragments.BasicCalculator;
import com.org.ait.calc.fragments.GSTStructure;
import com.org.ait.calc.fragments.LinkFragment;
import com.org.ait.calc.constats.Constant;
import com.org.ait.calc.util.Logger;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //displayPage(R.id.nav_home);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new BasicCalculator(), "home").commit();

        //MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        MobileAds.initialize(this,Constant.APP_ID);

        View header = navigationView.getHeaderView(0);
        AdView mAdView = header.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("242BD40977CEFA2B86CDBC8D7C098279")
                .build();
        mAdView.loadAd(adRequest);

        AdView mAdView1 = toolbar.findViewById(R.id.adView);
        mAdView1.loadAd(adRequest);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displayPage(id);
        return true;
    }

    private void displayPage(int id) {
        //tabLayout.setVisibility(View.GONE);
        if (id == R.id.nav_home) {
            callFragment(new BasicCalculator());
            //tabLayout.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_report) {

        } else if (id == R.id.nav_what_is_gst) {
            callFragment(new ActivityWhatIsGST());
        } else if (id == R.id.nav_gst_structure) {
            callFragment(new GSTStructure());
        } else if (id == R.id.nav_gst_rates) {
            callFragment(LinkFragment.newInstance("gstrates"));
        } else if (id == R.id.nav_imp_links) {
            callFragment(LinkFragment.newInstance("impcontacts"));
        } else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_rat_us) {
            rateUs();
        } else if (id == R.id.nav_contact_us) {
            contactUs();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void callFragment(Fragment fragment) {
        try {
            //tabLayout.setVisibility(View.GONE);
            String tag = fragment.getClass().getSimpleName();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragment, tag).addToBackStack(tag).commit();
        } catch (Exception e) {
            Logger.onErr(e);
        }
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    private void shareApp() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, Constant.SUBJECT);
            String sAux = Constant.MSG + Constant.APPSTORE_LINK + getPackageName();
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            Logger.onErr(e);
        }
    }

    private void rateUs() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constant.APPSTORE_LINK + getPackageName())));
        }
    }

    private void contactUs() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"appindiatech@gmail.com"});
        //i.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        //i.putExtra(android.content.Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(i, "Send email"));
    }

}
