package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;


import com.example.adapter.SectionVideoPagerAdapter;
import com.example.news.R;
import com.google.android.material.tabs.TabLayout;


public class VideoPage extends BaseFragment {
    private TabLayout tabLayout;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitTab();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_video_page, null);
        return view;
    }
    private void InitTab()
    {
        tabLayout = getActivity().findViewById(R.id.videotab);
        SectionVideoPagerAdapter sectionVideoPagerAdapter = new SectionVideoPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());
        ViewPager viewPager = getActivity().findViewById(R.id.video_view_pager);
        viewPager.setAdapter(sectionVideoPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
