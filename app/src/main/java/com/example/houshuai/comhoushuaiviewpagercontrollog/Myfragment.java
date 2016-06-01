package com.example.houshuai.comhoushuaiviewpagercontrollog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by HouShuai on 2016/5/30.
 */

public class Myfragment  extends ListFragment{


    private String name;
    private int index;
    private TextView tv_show;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        index = bundle.getInt("index",0);
        name = bundle.getString("name","");
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logactivity,container,false);
        tv_show = (TextView) view.findViewById(R.id.tv_show);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_show.setText("您现在查看的是{"+name+"}页面，呵呵。。。。。");
        List<Map<String, Object>> data = new LinkedList<>();
        for (int i=0;i<100;i++) {
            Map<String, Object> map = new LinkedHashMap<>();

            map.put("tv_name_id", name+i);
            data.add(map);
}SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, R.layout.item,
                new String[] { "tv_name_id"  },
                new int[] {  R.id.tv_name_id});

        // 经典之处：将适配器设置到ListView中
        setListAdapter(adapter);


    }
}
