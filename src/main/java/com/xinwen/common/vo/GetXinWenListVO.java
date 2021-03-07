package com.xinwen.common.vo;

import com.xinwen.common.entity.XinWenEntity;
import com.xinwen.common.param.GetXinWenListParam;
import lombok.Data;

import java.util.List;

/**
 * com.xinwen.common.vo
 *
 * @author 庄先生
 * @date 2021/3/7
 */
@Data
public class GetXinWenListVO {

    private GetXinWenListParam pageparam;
    private List<XinWenEntity> pagelist;

}
