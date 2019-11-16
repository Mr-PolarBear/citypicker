package com.ihidea.as.citypicker.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ihidea.as.citypicker.R;
import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citythreelist.AreaActivity;
import com.lljjcoder.style.citythreelist.AreaActivity_Mult;
import com.lljjcoder.style.citythreelist.CityBean;
import com.lljjcoder.style.citythreelist.ProvinceActivity;

import java.util.List;

public class CitypickerThreeListActivity extends AppCompatActivity {
    TextView mListTv;

    TextView mResultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citypicker_three_list);
        findView();
    }

    private void findView() {
        mListTv = (TextView) findViewById(R.id.list_tv);
        mResultTv = (TextView) findViewById(R.id.result_tv);

        mListTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list();
            }
        });
    }

    public void list() {
        new AlertDialog.Builder(this).setMessage("选择单选或多选").setPositiveButton("多选", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProvinceActivity.Launch(CitypickerThreeListActivity.this, true);
            }
        }).setNegativeButton("单选", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProvinceActivity.Launch(CitypickerThreeListActivity.this, false);
            }
        }).show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProvinceActivity.RESULT_DATA) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }


                CityBean city = data.getParcelableExtra("city");
                CityBean province = data.getParcelableExtra("province");
                List<CityInfoBean> areaList = data.getParcelableArrayListExtra(AreaActivity_Mult.INTENT_AREA_LIST);
                CityBean area = data.getParcelableExtra(AreaActivity.INTENT_AREA);
                if (area == null)
                    area = new CityBean();

                mResultTv.setText("所选省市区城市： " + province.getName() + " " + province.getId() + "\n" + city.getName()
                        + " " + city.getId() + "\n" + area.getName() + " " + area.getId() + "\n");
            }
        }
    }
}
