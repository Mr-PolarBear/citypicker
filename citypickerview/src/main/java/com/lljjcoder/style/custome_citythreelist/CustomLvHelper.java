package com.lljjcoder.style.custome_citythreelist;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for:
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
     * CustomLvHelper.ourInstance().
     *
     *
     */


    private OnCustonListener mListnener;


    private List<ICustomLvBean> mList = new ArrayList<>(); //所有的3级列表

    private ICustomLvBean mSelectLv1;//已经选中的集合第一层(省) 传进去 变为选中状态   暂时没用
    private ICustomLvBean mSelectLv2;//已经选中的集合第二层(市) 传进去 变为选中状态   暂时没用
    private List<ICustomLvBean> mSelectLv3List = new ArrayList<>();//已经选中的集合第三层(区) 传进去 变为选中状态


    protected ICustomLvBean mInternal_Lv1;//已经选中的集合第一层(省)  内部用来存储
    protected ICustomLvBean mInternal_Lv2;//已经选中的集合第二层(市)   内部用来存储
    protected List<ICustomLvBean> mInternal_Lv3List = new ArrayList<>();//已经选中的集合第三层(区)   内部用来存储


    private boolean isMult = false;//是否多选
    private int hierarchy = 3;//层级  深度  1 2 3


    //设置数据1
    public CustomLvHelper setData(List<ICustomLvBean> list) {
        mList = list;
        return this;
    }


    public void showPicker(Activity content, List<ICustomLvBean> lv3, boolean isMult, int hierarchy, OnCustonListener listener) {
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

    public ICustomLvBean getmSelectLv1() {
        return mSelectLv1;
    }

    public ICustomLvBean getmSelectLv2() {
        return mSelectLv2;
    }

    public List<ICustomLvBean> getmSelectLv3() {
        if (mSelectLv3List == null)
            mSelectLv3List = new ArrayList<>();
        return mSelectLv3List;
    }

    public List<ICustomLvBean> getList() {
        if (mList == null)
            mList = new ArrayList<>();
        return mList;
    }

    public OnCustonListener getListnener() {
        return mListnener;

    }

    public interface OnCustonListener {
        void onSelectOk(ICustomLvBean lv1, ICustomLvBean lv2, List<ICustomLvBean> lv3);
    }
}
