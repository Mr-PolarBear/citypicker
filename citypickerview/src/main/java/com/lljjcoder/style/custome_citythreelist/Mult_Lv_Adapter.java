package com.lljjcoder.style.custome_citythreelist;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lljjcoder.style.citypickerview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lljjcoder.style.custome_citythreelist.Lv1Activity.LV1_RESULT_DATA;


/**
 * 作者：liji on 2017/12/16 15:13
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class Mult_Lv_Adapter extends RecyclerView.Adapter<Mult_Lv_Adapter.MyViewHolder> {

    List<ICustomLvBean> cityList = new ArrayList<>();
    public Map<String, ICustomLvBean> mArray = new HashMap<>();

    Activity context;


    public Mult_Lv_Adapter(Activity context, List<ICustomLvBean> cityList) {
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
        final ICustomLvBean city = cityList.get(position);
        holder.tv.setText(city.getLvShowName());
        holder.tv.setSelected(mArray.get(city.getLvShowId()) != null);


        if (!CustomLvHelper.getInstance().isMult()) {
            holder.tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            holder.tv.setBackgroundResource(R.drawable.city_selector_white);
        }

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!CustomLvHelper.getInstance().isMult()) {
                    CustomLvHelper.getInstance().mInternal_Lv3List = new ArrayList();
                    CustomLvHelper.getInstance().mInternal_Lv3List.add(city);
                    context.setResult(LV1_RESULT_DATA);
                    context.finish();
                    return;
                }
                if (mArray.get(city.getLvShowId()) != null) {
                    mArray.remove(city.getLvShowId());
                } else {
                    mArray.put(city.getLvShowId(), city);
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
    public List<ICustomLvBean> getSelectList() {
        List<ICustomLvBean> arrayList = new ArrayList<ICustomLvBean>();
        for (Map.Entry<String, ICustomLvBean> entry : mArray.entrySet()) {
            arrayList.add(entry.getValue());
        }
        return arrayList;
    }
}
