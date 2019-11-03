package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.example.adapter.MinePageAdapter;
import com.example.adapter.MinePageDynamicAdapter;
import com.example.adapter.MinePagePersonAdapter;
import com.example.entity.MineDynamic;
import com.example.entity.MinePageA;
import com.example.entity.MinePageInformation;
import com.example.entity.MinePagePerson;
import com.example.news.R;
import com.service.MinePageDynamicService;
import com.service.MinePagePersonService;
import com.service.MinePageService;

import java.util.ArrayList;
import java.util.List;


public class MinePage extends BaseFragment {
    MinePageDynamicService minePageDynamicService;
    RecyclerView recyclerViewDynamic;

    MinePagePersonService minePagePersonService;
    RecyclerView recyclerViewPerson;

    MinePageService minePageService;
    RecyclerView recyclerViewA;

    private List<MineDynamic> listMineDynamic = new ArrayList<>();
    private List<MinePagePerson> listMinePerson = new ArrayList<>();
    private List<MinePageA> listMineA = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Dynamic
        minePageDynamicService = MinePageDynamicService.getInstance();
        listMineDynamic = minePageDynamicService.getList();
        recyclerViewDynamic = getActivity().findViewById(R.id.recycler_view_mine_dynamic);
        LinearLayoutManager layoutManagerDynamic = new LinearLayoutManager(getActivity());
        layoutManagerDynamic.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewDynamic.setLayoutManager(layoutManagerDynamic);
        MinePageDynamicAdapter minePageDynamicAdapter =new MinePageDynamicAdapter(listMineDynamic);
        recyclerViewDynamic.setAdapter(minePageDynamicAdapter);

        //Person
        minePagePersonService = MinePagePersonService.getInstance();
        listMinePerson = minePagePersonService.getList();
        recyclerViewPerson = getActivity().findViewById(R.id.recycler_view_mine_person);
        RecyclerView.LayoutManager layoutManagerPerson = new LinearLayoutManager(getActivity());
        recyclerViewPerson.setLayoutManager(layoutManagerPerson);
        MinePagePersonAdapter minePagePersonAdapter = new MinePagePersonAdapter(listMinePerson);
        recyclerViewPerson.setAdapter(minePagePersonAdapter);

        //Information
        minePageService = MinePageService.getInstance();
        listMineA = minePageService.getList();
        recyclerViewA = getActivity().findViewById(R.id.recycler_view_InformationA);
        RecyclerView.LayoutManager layoutManagerA = new LinearLayoutManager(getActivity());
        recyclerViewA.setLayoutManager(layoutManagerA);
        MinePageAdapter minePageAdapter = new MinePageAdapter(listMineA);
        recyclerViewA.setAdapter(minePageAdapter);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine_page, null);
        return view;
    }


}
