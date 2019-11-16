package com.lljjcoder.style.custome_citythreelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lljjcoder.style.citypickerview.R;
import com.lljjcoder.widget.RecycleViewDividerForList;

import java.util.List;

public class Lv1Activity extends Activity {

    private TextView mCityNameTv;

    private RecyclerView mCityRecyclerView;

    private ImageView mImgBack;
    public static final int LV1_RESULT_DATA = 10321;

    public static void Launch(Activity activity) {
        Intent intent = new Intent(activity, Lv1Activity.class);
        activity.startActivity(intent);
    }

    private CustomLvHelper mHelper = CustomLvHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_citylist);
        initView();
        setData();

    }

    private void setData() {

        final List<ICustomLvBean> cityList = mHelper.getList();
        if (cityList == null) {
            return;
        }

        Lv_Adapter lvAdapter = new Lv_Adapter(Lv1Activity.this, cityList);
        mCityRecyclerView.setAdapter(lvAdapter);
        lvAdapter.setOnItemClickListener(new Lv_Adapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position) {
                mHelper.mInternal_Lv1 = cityList.get(position);

                //1级
                if (mHelper.getHierarchy() == 1) {
                    finish();
                    mHelper.getListnener().onSelectOk(mHelper.mInternal_Lv1, null, null);
                    return;
                }
                //2级
                if (mHelper.getHierarchy() == 2) {
                    isToMult(Lv1Activity.this);
                    return;
                }

                //3级
                Lv2Activity.Launch(Lv1Activity.this);

            }
        });
    }

    protected static void isToMult(Activity context) {
        if (CustomLvHelper.getInstance().isMult())//是否多选 等待完善
            Lv3Activity.Launch(context);
        else
            Lv3Activity.Launch(context);
    }

    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_left);
        mImgBack.setVisibility(View.VISIBLE);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCityNameTv = (TextView) findViewById(R.id.cityname_tv);
        mCityNameTv.setText("选择省份");
        mCityRecyclerView = (RecyclerView) findViewById(R.id.city_recyclerview);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.addItemDecoration(new RecycleViewDividerForList(this, LinearLayoutManager.HORIZONTAL, true));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LV1_RESULT_DATA) {
            setResult(RESULT_OK);
            mHelper.getListnener().onSelectOk(mHelper.mInternal_Lv1, mHelper.mInternal_Lv2, mHelper.mInternal_Lv3List);
            finish();
        }
    }

}
