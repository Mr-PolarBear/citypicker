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

import static com.lljjcoder.style.custome_citythreelist.Lv1Activity.LV1_RESULT_DATA;
import static com.lljjcoder.style.custome_citythreelist.Lv1Activity.isToMult;

public class Lv2Activity extends Activity {

    private TextView mCityNameTv;

    private ImageView mImgBack;

    private RecyclerView mCityRecyclerView;


    private CustomLvHelper mHelper = CustomLvHelper.getInstance();


    public static void Launch(Activity activity) {
        Intent intent = new Intent(activity, Lv2Activity.class);
        activity.startActivityForResult(intent, LV1_RESULT_DATA);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        initView();

        setData();

    }

    private void setData() {
        mCityNameTv.setText(mHelper.mInternal_Lv1.getLvShowName());

        final List<ICustomLvBean> cityList = mHelper.mInternal_Lv1.getLvShowList();
        if (cityList == null) {
            return;
        }

        Lv_Adapter lvAdapter = new Lv_Adapter(Lv2Activity.this, cityList);
        mCityRecyclerView.setAdapter(lvAdapter);
        lvAdapter.setOnItemClickListener(new Lv_Adapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position) {
                mHelper.mInternal_Lv2 = cityList.get(position);

                //多选或者单选
                isToMult(Lv2Activity.this);
            }
        });

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
        if (resultCode == LV1_RESULT_DATA) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
