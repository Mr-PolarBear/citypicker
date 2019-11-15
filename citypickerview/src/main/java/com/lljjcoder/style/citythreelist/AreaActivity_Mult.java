package com.lljjcoder.style.citythreelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citypickerview.R;
import com.lljjcoder.widget.RecycleViewDividerForList;

import java.util.ArrayList;
import java.util.List;

import static com.lljjcoder.style.citylist.utils.CityListLoader.BUNDATA;
import static com.lljjcoder.style.citythreelist.ProvinceActivity.RESULT_DATA;

//多选
public class AreaActivity_Mult extends Activity {

    private TextView mCityNameTv;

    private ImageView mImgBack;

    private RecyclerView mCityRecyclerView;

    private CityInfoBean mProCityInfo = null;

    private TextView mTvOK;

    private Mult_CityAdapter cityAdapter;

    public static final String INTENT_AREA_LIST = "arealist";

    public static void Launch(Activity activity, CityInfoBean bean) {
        Intent intent = new Intent(activity, AreaActivity_Mult.class);
        intent.putExtra(BUNDATA, bean);
        activity.startActivityForResult(intent, RESULT_DATA);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        mProCityInfo = this.getIntent().getParcelableExtra(BUNDATA);
        initView();

        setData();

        mTvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cityAdapter.mArray.size() == 0) {
                    Toast.makeText(AreaActivity_Mult.this, "请至少选择一项", Toast.LENGTH_SHORT).show();
                    return;
                }

                //将计算的结果回传给第一个Activity
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(INTENT_AREA_LIST, (ArrayList<? extends Parcelable>) cityAdapter.getSelectList());
                setResult(RESULT_DATA, intent);
                //退出第二个Activity
                finish();
            }
        });

    }

    private void setData() {

        if (mProCityInfo != null && mProCityInfo.getCityList().size() > 0) {
            mCityNameTv.setText("" + mProCityInfo.getName());

            final List<CityInfoBean> cityList = mProCityInfo.getCityList();
            if (cityList == null) {
                return;
            }

            cityAdapter = new Mult_CityAdapter(AreaActivity_Mult.this, cityList);
            mCityRecyclerView.setAdapter(cityAdapter);

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
