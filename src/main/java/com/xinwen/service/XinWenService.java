package com.xinwen.service;

import com.xinwen.common.entity.XinWenEntity;
import com.xinwen.common.param.GetXinWenListParam;
import com.xinwen.common.utils.RResult;

/**
 * com.xinwen.service
 *
 * @author 庄先生
 * @date 2021/3/7
 */
public interface XinWenService {

    RResult getXinWenList(RResult result, GetXinWenListParam param);


    RResult getXinWenType(RResult result);

}
