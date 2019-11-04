package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.adapter.MinePageInformationAdapter;
import com.example.adapter.MinePageDynamicAdapter;
import com.example.adapter.MinePagePersonAdapter;
import com.example.entity.MineDynamic;
import com.example.entity.MinePageInformation;
import com.example.entity.MinePagePerson;
import com.example.news.R;
import com.service.MinePageDynamicService;
import com.service.MinePagePersonService;
import com.service.MinePageInformationService;

import java.util.ArrayList;
import java.util.List;


public class MinePage extends BaseFragment {
    static int MINE_PAGE_REQUESTCODE =500;
    MinePageDynamicService minePageDynamicService;
    RecyclerView recyclerViewDynamic;

    MinePagePersonService minePagePersonService;
    RecyclerView recyclerViewPerson;

    MinePageInformationService minePageService;
    RecyclerView recyclerViewA;



    private List<MineDynamic> listMineDynamic = new ArrayList<>();
    private List<MinePagePerson> listMinePerson = new ArrayList<>();
    private List<MinePageInformation> listMineA = new ArrayList<>();

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
        minePageService = MinePageInformationService.getInstance();
        listMineA = minePageService.getList();
        recyclerViewA = getActivity().findViewById(R.id.recycler_view_InformationA);
        RecyclerView.LayoutManager layoutManagerA = new LinearLayoutManager(getActivity());
        recyclerViewA.setLayoutManager(layoutManagerA);
        MinePageInformationAdapter minePageAdapter = new MinePageInformationAdapter(listMineA);
        recyclerViewA.setAdapter(minePageAdapter);




    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine_page, null);
        return view;
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


}
