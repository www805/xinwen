package com.xinwen.common.param;

import com.xinwen.common.utils.Page;
import lombok.Data;

/**
 * com.xinwen.common.param
 *
 * @author 庄先生
 * @date 2021/3/7
 */
@Data
public class GetXinWenListParam extends Page {

    private String title;//文章标题
    private String type;//文章类型


}
