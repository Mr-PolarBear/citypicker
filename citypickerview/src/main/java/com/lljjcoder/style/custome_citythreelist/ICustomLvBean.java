package com.lljjcoder.style.custome_citythreelist;

import java.util.List;

/**
 * Class for:
 * Created by   jack.马
 * Date: 2019/11/16
 * Time: 17:41
 */
public interface ICustomLvBean {
    String getLvShowName();

    String getLvShowId();

    List<ICustomLvBean> getLvShowList();
}
