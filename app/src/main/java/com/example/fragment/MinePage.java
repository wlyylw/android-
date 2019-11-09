package com.example.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;


public class MinePage extends Fragment {
    ImageView imageView;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MinePagePersonService minePagePersonService = MinePagePersonService.getInstance();
        Toast.makeText(getActivity(), "欢迎您:" + minePagePersonService.getPhonenumber(), Toast.LENGTH_SHORT).show();

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
        minePagePersonService = MinePagePersonService.getInstance();
        listMinePerson = minePagePersonService.getList();
        MinePagePerson minePagePerson = minePagePersonService.getMinePagePerson();
        listMinePerson.set(0,minePagePerson);
        recyclerViewPerson = getActivity().findViewById(R.id.recycler_view_mine_person);
        RecyclerView.LayoutManager layoutManagerPerson = new LinearLayoutManager(getActivity());
        recyclerViewPerson.setLayoutManager(layoutManagerPerson);
        MinePagePersonAdapter minePagePersonAdapter = new MinePagePersonAdapter(listMinePerson);
        recyclerViewPerson.setAdapter(minePagePersonAdapter);

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
