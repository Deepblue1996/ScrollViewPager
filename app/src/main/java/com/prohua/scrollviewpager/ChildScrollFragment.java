package com.prohua.scrollviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prohua.scrollviewpager.weight.CustomViewPager;
import com.prohua.universal.DefaultAdapter;
import com.prohua.universal.DefaultViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * ScrollView Fragment
 * Created by Deep on 2018/2/2 0002.
 */

public class ChildScrollFragment extends Fragment {

    public ChildScrollFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_my, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }

}