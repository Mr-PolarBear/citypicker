package com.lljjcoder.style.citythreelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.lljjcoder.style.citypickerview.R;
import com.lljjcoder.widget.RecycleViewDividerForList;

import java.util.ArrayList;
import java.util.List;

import static com.lljjcoder.style.citylist.utils.CityListLoader.BUNDATA;

public class ProvinceActivity extends Activity {

    private TextView mCityNameTv;

    private RecyclerView mCityRecyclerView;

    public static final int RESULT_DATA = 1001;
    private static final String INTENT_ISMULT = "iSmult";

    private CityBean provinceBean = new CityBean();

    protected static boolean isMultiple = false;//是否多选

    public static void Launch(Activity activity, boolean isMultiple) {
        Intent intent = new Intent(activity, ProvinceActivity.class);
        intent.putExtra(INTENT_ISMULT, isMultiple);
        activity.startActivityForResult(intent, RESULT_DATA);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMultiple = getIntent().getBooleanExtra(INTENT_ISMULT, false);
        setContentView(R.layout.activity_citylist);
        initView();
        setData();

    }

    private void setData() {

        final List<CityInfoBean> cityList = CityListLoader.getInstance().getProListData();
        if (cityList == null) {
            return;
        }

        CityAdapter cityAdapter = new CityAdapter(ProvinceActivity.this, cityList);
        mCityRecyclerView.setAdapter(cityAdapter);
        cityAdapter.setOnItemClickListener(new CityAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position) {

                provinceBean.setId(cityList.get(position).getId());
                provinceBean.setName(cityList.get(position).getName());
                Intent intent = new Intent(ProvinceActivity.this, CityActivity.class);
                intent.putExtra(BUNDATA, cityList.get(position));
                startActivityForResult(intent, RESULT_DATA);

            }
        });

    }

    private void initView() {
        mCityNameTv = (TextView) findViewById(R.id.cityname_tv);
        mCityNameTv.setText("选择省份");
        mCityRecyclerView = (RecyclerView) findViewById(R.id.city_recyclerview);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.addItemDecoration(new RecycleViewDividerForList(this, LinearLayoutManager.HORIZONTAL, true));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_DATA && data != null) {
            CityBean area = data.getParcelableExtra(AreaActivity.INTENT_AREA);
            CityBean city = data.getParcelableExtra("city");
            List<CityBean> areaList = data.getParcelableArrayListExtra(AreaActivity_Mult.INTENT_AREA_LIST);

            Intent intent = new Intent();
            intent.putExtra("province", provinceBean);
            intent.putExtra("city", city);
            intent.putExtra(AreaActivity.INTENT_AREA, area == null ? new CityBean() : area);
            intent.putParcelableArrayListExtra(AreaActivity_Mult.INTENT_AREA_LIST, (ArrayList<? extends Parcelable>) areaList);
            setResult(RESULT_OK, intent);


            finish();
        }
    }

}
