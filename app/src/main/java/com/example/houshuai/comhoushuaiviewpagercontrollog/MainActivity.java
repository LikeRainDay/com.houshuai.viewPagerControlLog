package com.example.houshuai.comhoushuaiviewpagercontrollog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.LinkedList;

public class MainActivity extends FragmentActivity{


    private RadioGroup rg_id;
    private LinkedList<Fragment> data;
    private ViewPager vp_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化各个控件
        initalControalButton();
//初始化radioGroup
        initalRadioGroup();
//关于viewPager操作
initalViewPager();
    }

    private void initalViewPager() {
        data = new LinkedList<>();
        fillDataToViewPager();
        Myadapter myadapter = new Myadapter(getSupportFragmentManager());
        vp_show.setAdapter(myadapter);
vp_show.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        for (int i=0;i<rg_id.getChildCount();i++) {
            RadioButton childAt = (RadioButton) rg_id.getChildAt(position);
            childAt.setChecked(false);
        }
        ((RadioButton) rg_id.getChildAt(position)).setChecked(true);
    }
});

    }

    private void fillDataToViewPager() {
        for (int i=0;i<rg_id.getChildCount();i++) {
            Myfragment fragment = new Myfragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index",i);
            bundle.putString("name",((RadioButton)rg_id.getChildAt(i)).getText().toString());
            fragment.setArguments(bundle);
            data.add(fragment);
        }



    }

    private void initalRadioGroup() {
        rg_id.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
             for (int i=0;i<group.getChildCount();i++) {
                 RadioButton childAt =(RadioButton) group.getChildAt(i);
                 if (childAt.getId() == checkedId) {
                     vp_show.setCurrentItem(i);
                     break;
                 }
             }
            }
        });

    }

    private void initalControalButton() {
        rg_id = (RadioGroup) findViewById(R.id.rg_id);
        vp_show = (ViewPager) findViewById(R.id.vp_show);
    }

    public  final  class Myadapter extends FragmentStatePagerAdapter {


        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return data.size();
        }
        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }
}
