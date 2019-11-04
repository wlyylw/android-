package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.adapter.SectionCirclePagerAdapter;
import com.example.adapter.SectionsIndexPagerAdapter;
import com.example.news.R;
import com.google.android.material.tabs.TabLayout;


public class CirclePage extends BaseFragment {

    private TabLayout tabLayout;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitTab();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circle_page, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    private void InitTab()
    {
        tabLayout = getActivity().findViewById(R.id.circletab);
        SectionCirclePagerAdapter sectionCirclePagerAdapter = new SectionCirclePagerAdapter(getActivity(), getActivity().getSupportFragmentManager());
        ViewPager viewPager = getActivity().findViewById(R.id.circle_view_pager);
        viewPager.setAdapter(sectionCirclePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
