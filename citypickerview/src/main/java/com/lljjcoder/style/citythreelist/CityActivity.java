package com.lljjcoder.style.citythreelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citypickerview.R;
import com.lljjcoder.widget.RecycleViewDividerForList;

import java.util.ArrayList;
import java.util.List;

import static com.lljjcoder.style.citylist.utils.CityListLoader.BUNDATA;
import static com.lljjcoder.style.citythreelist.ProvinceActivity.RESULT_DATA;

public class CityActivity extends Activity {

    private TextView mCityNameTv;

    private ImageView mImgBack;

    private RecyclerView mCityRecyclerView;

    private CityInfoBean mProInfo = null;

    private String cityName = "";

    private CityBean cityBean = new CityBean();

    private CityBean area = new CityBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        mProInfo = this.getIntent().getParcelableExtra(BUNDATA);
        initView();

        setData(mProInfo);

    }

    private void setData(CityInfoBean mProInfo) {

        if (mProInfo != null && mProInfo.getCityList().size() > 0) {
            mCityNameTv.setText("" + mProInfo.getName());

            final List<CityInfoBean> cityList = mProInfo.getCityList();
            if (cityList == null) {
                return;
            }

            CityAdapter cityAdapter = new CityAdapter(CityActivity.this, cityList);
            mCityRecyclerView.setAdapter(cityAdapter);
            cityAdapter.setOnItemClickListener(new CityAdapter.OnItemSelectedListener() {
                @Override
                public void onItemSelected(View view, int position) {

                    cityBean.setId(cityList.get(position).getId());
                    cityBean.setName(cityList.get(position).getName());

                    //多选或者单选
                    if (ProvinceActivity.isMultiple)
                        AreaActivity_Mult.Launch(CityActivity.this, cityList.get(position));
                    else
                        AreaActivity.Launch(CityActivity.this, cityList.get(position));


                }
            });

        }
    }

    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_left);
        mCityNameTv = (TextView) findViewById(R.id.cityname_tv);
        mImgBack.setVisibility(View.VISIBLE);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCityNameTv = (TextView) findViewById(R.id.cityname_tv);
        mCityRecyclerView = (RecyclerView) findViewById(R.id.city_recyclerview);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.addItemDecoration(new RecycleViewDividerForList(this, LinearLayoutManager.HORIZONTAL, true));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_DATA && data != null) {
            area = data.getParcelableExtra(AreaActivity.INTENT_AREA);
            List<CityBean> areaList = data.getParcelableArrayListExtra(AreaActivity_Mult.INTENT_AREA_LIST);

            Intent intent = new Intent();
            intent.putExtra("city", cityBean);
            intent.putExtra(AreaActivity.INTENT_AREA, area);
            intent.putParcelableArrayListExtra(AreaActivity_Mult.INTENT_AREA_LIST, (ArrayList<? extends Parcelable>) areaList);

            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
