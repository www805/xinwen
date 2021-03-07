package com.xinwen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinwen.common.entity.XinWenEntity;

import java.util.List;

/**
 * com.xinwen.mapper
 *
 * @author 庄先生
 * @date 2021/2/27
 */
public interface XinWenMapper extends BaseMapper<XinWenEntity> {


    List<String> getXinWenType();

}
