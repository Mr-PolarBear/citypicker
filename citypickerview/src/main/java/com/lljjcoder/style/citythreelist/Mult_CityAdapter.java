package com.lljjcoder.style.citythreelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citypickerview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：liji on 2017/12/16 15:13
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class Mult_CityAdapter extends RecyclerView.Adapter<Mult_CityAdapter.MyViewHolder> {

    List<CityInfoBean> cityList = new ArrayList<>();
    public Map<String, CityInfoBean> mArray = new HashMap<>();

    Context context;


    public Mult_CityAdapter(Context context, List<CityInfoBean> cityList) {
        this.cityList = cityList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_citylist_mult, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final CityInfoBean city = cityList.get(position);
        holder.tv.setText(city.getName());
        holder.tv.setSelected(mArray.get(city.getId()) != null);


        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mArray.get(city.getId()) != null) {
                    mArray.remove(city.getId());
                } else {
                    mArray.put(city.getId(), city);
                }
                notifyDataSetChanged();


            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.default_item_city_name_tv);
        }
    }


    //获取选中集合
    public List<CityInfoBean> getSelectList() {
        List<CityInfoBean> arrayList = new ArrayList<CityInfoBean>();
        for (Map.Entry<String, CityInfoBean> entry : mArray.entrySet()) {
            arrayList.add(entry.getValue());
        }
        return arrayList;
    }
}
