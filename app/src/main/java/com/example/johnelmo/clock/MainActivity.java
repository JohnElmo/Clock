package com.example.johnelmo.clock;

import android.app.DatePickerDialog;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    static SectionsPagerAdapter mSectionsPagerAdapter;
    static ViewPager mViewPager;
    static TabLayout tabLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Model m = new Model();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.container);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        setViewPager(mSectionsPagerAdapter, mViewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    public void setViewPager(SectionsPagerAdapter sectionsPagerAdapter, ViewPager viewPager) {
        sectionsPagerAdapter.addFragment(new ControlPanelFragment(), "Control Panel");
        sectionsPagerAdapter.addFragment(new DigitalClockFragment(), "Digital Clock");
        sectionsPagerAdapter.addFragment(new AnalogClockFragment(), "Analog Clock");
        viewPager.setAdapter(sectionsPagerAdapter);
    }

    public static void updatePageAdapter(Fragment newFragment, String title) {
        mSectionsPagerAdapter.addFragment(newFragment, title);

        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

}