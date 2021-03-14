package com.xinwen.common.param;

import com.xinwen.common.utils.Page;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * com.xinwen.common.param
 *
 * @author 庄先生
 * @date 2021/3/14
 */
@Data
public class GetSearchXinWenParam extends Page {

    @NotBlank(message = "关键字不能为空！")
    private String keyword;

}
