package com.lljjcoder.style.custome_citythreelist;

import java.util.List;

/**
 * Class for:
 * Created by   jack.马
 * Date: 2019/11/16
 * Time: 17:41
 */
public abstract class AbsCustomLvBean {

    public boolean isHead = false;

    public String parentId;

    public abstract String getLvShowName();

    public abstract String getLvShowId();

    public abstract List<AbsCustomLvBean> getLvShowList();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsCustomLvBean out = (AbsCustomLvBean) o;
        return getLvShowName().equals(out.getLvShowName()) || getLvShowId().equals(out.getLvShowId());
    }

    @Override
    public int hashCode() {
        return getLvShowId().hashCode();
    }
}
