package com.prohua.scrollviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prohua.universal.DefaultAdapter;
import com.prohua.universal.DefaultViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deep on 2018/2/2 0002.
 */

public class MyFragment extends Fragment {
    private RecyclerView recyclerView;

    private DefaultAdapter defaultAdapter;
    private List<String> stringList;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        String title= (String) getArguments().get("title");
        stringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            stringList.add(title+":"+i);
        }
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        defaultAdapter = new DefaultAdapter(getContext(), stringList, R.layout.fragment_my_item, 0, 0);
        defaultAdapter.setOnBindItemView(new DefaultAdapter.OnBindItemView() {
            @Override
            public void onBindItemViewHolder(DefaultViewHolder defaultViewHolder, int i) {
                defaultViewHolder.setText(R.id.txt_title, stringList.get(i));
            }
        });
        recyclerView.setAdapter(defaultAdapter);
    }

}