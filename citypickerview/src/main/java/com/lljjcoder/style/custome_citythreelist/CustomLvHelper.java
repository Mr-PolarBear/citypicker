package com.lljjcoder.style.custome_citythreelist;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Class for:  不可用于同时存在栈内有多个页面调用到
 * Created by   jack.马
 * Date: 2019/11/16
 * Time: 17:30
 */
public class CustomLvHelper {
    private static final CustomLvHelper ourInstance = new CustomLvHelper();

    public static CustomLvHelper getInstance() {
        return ourInstance;
    }


    private CustomLvHelper() {

    }

    /* 用法
     ****
     *
     * ExpandeLvHelper.ourInstance().
     *
     *
     */


    private OnCustonListener mListnener;


    private List<AbsCustomLvBean> mList = new ArrayList<>(); //所有的3级列表

    private AbsCustomLvBean mSelectLv1;//已经选中的集合第一层(省) 传进去 变为选中状态   暂时没用
    private AbsCustomLvBean mSelectLv2;//已经选中的集合第二层(市) 传进去 变为选中状态   暂时没用
    private List<AbsCustomLvBean> mSelectLv3List = new ArrayList<>();//已经选中的集合第三层(区) 传进去 变为选中状态


    protected AbsCustomLvBean mInternal_Lv1;//已经选中的集合第一层(省)  内部用来存储
    protected AbsCustomLvBean mInternal_Lv2;//已经选中的集合第二层(市)   内部用来存储
    protected HashSet<AbsCustomLvBean> mInternal_Lv3List = new HashSet<>();//已经选中的集合第三层(区)   内部用来存储


    private boolean isMult = false;//是否多选
    private int hierarchy = 3;//层级  深度  1 2 3


    //设置数据1
    public CustomLvHelper setData(List<AbsCustomLvBean> list) {
        mList = list;
        return this;
    }


    public void showPicker(Activity content, List<AbsCustomLvBean> lv3, boolean isMult, int hierarchy, OnCustonListener listener) {
        if (listener == null)
            throw new NullPointerException("必须传入监听器");
        this.isMult = isMult;
        this.hierarchy = hierarchy;
        mSelectLv3List = lv3;
        mListnener = listener;
        Lv1Activity.Launch(content);
    }

    public boolean isMult() {
        return isMult;
    }


    public int getHierarchy() {
        return hierarchy;
    }

    public AbsCustomLvBean getmSelectLv1() {
        return mSelectLv1;
    }

    public AbsCustomLvBean getmSelectLv2() {
        return mSelectLv2;
    }

    public List<AbsCustomLvBean> getmSelectLv3() {
        if (mSelectLv3List == null)
            mSelectLv3List = new ArrayList<>();
        return mSelectLv3List;
    }

    public List<AbsCustomLvBean> getList() {
        if (mList == null)
            mList = new ArrayList<>();
        return mList;
    }

    public OnCustonListener getListnener() {
        return mListnener;

    }

    public interface OnCustonListener {
        void onSelectOk(AbsCustomLvBean lv1, AbsCustomLvBean lv2, HashSet<AbsCustomLvBean> lv3);
    }
}
