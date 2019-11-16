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
import android.widget.Toast;

import com.lljjcoder.style.citypickerview.R;
import com.lljjcoder.widget.RecycleViewDividerForList;

import java.util.List;

import static com.lljjcoder.style.custome_citythreelist.Lv1Activity.LV1_RESULT_DATA;


//多选 或者单选
public class Lv3Activity extends Activity {

    private TextView mCityNameTv;

    private ImageView mImgBack;

    private RecyclerView mCityRecyclerView;


    private TextView mTvOK;

    private Mult_Lv_Adapter cityAdapter;

    public static final String INTENT_AREA_LIST = "arealist";

    private CustomLvHelper mHelper = CustomLvHelper.getInstance();


    public static void Launch(Activity activity) {
        Intent intent = new Intent(activity, Lv3Activity.class);
        activity.startActivityForResult(intent, LV1_RESULT_DATA);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_citylist);
        initView();
        if (!mHelper.isMult()) {
            mTvOK.setVisibility(View.GONE);
        }

        setData();

        mTvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cityAdapter.mArray.size() == 0) {
                    Toast.makeText(Lv3Activity.this, "请至少选择一项", Toast.LENGTH_SHORT).show();
                    return;
                }
                mHelper.mInternal_Lv3List = cityAdapter.getSelectList();
                //将计算的结果回传给第一个Activity
                setResult(LV1_RESULT_DATA);
                //退出第二个Activity
                finish();
            }
        });

    }

    private void setData() {
        final List<ICustomLvBean> cityList;
        if (mHelper.getHierarchy() == 3) {
            mCityNameTv.setText(mHelper.mInternal_Lv2.getLvShowName());
            cityList = mHelper.mInternal_Lv2.getLvShowList();
        } else {
            mCityNameTv.setText(mHelper.mInternal_Lv1.getLvShowName());
            cityList = mHelper.mInternal_Lv1.getLvShowList();
        }

        if (cityList == null) {
            return;
        }

        cityAdapter = new Mult_Lv_Adapter(Lv3Activity.this, cityList);
        mCityRecyclerView.setAdapter(cityAdapter);

        //如果外面已经传进来已经有选中的
        if (mHelper.getmSelectLv3() != null) {
            for (ICustomLvBean iCustomLvBean : mHelper.getmSelectLv3()) {
                cityAdapter.mArray.put(iCustomLvBean.getLvShowId(), iCustomLvBean);
            }
            cityAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        mTvOK = (TextView) findViewById(R.id.tv_city_mult);
        mTvOK.setVisibility(View.VISIBLE);
        mImgBack = (ImageView) findViewById(R.id.img_left);
        mCityNameTv = (TextView) findViewById(R.id.cityname_tv);
        mImgBack.setVisibility(View.VISIBLE);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCityRecyclerView = (RecyclerView) findViewById(R.id.city_recyclerview);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.addItemDecoration(new RecycleViewDividerForList(this, LinearLayoutManager.HORIZONTAL, true));

    }

}
