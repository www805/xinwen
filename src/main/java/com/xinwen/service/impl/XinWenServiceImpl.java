package com.xinwen.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinwen.common.entity.XinWenEntity;
import com.xinwen.common.param.GetXinWenListParam;
import com.xinwen.common.utils.RResult;
import com.xinwen.common.vo.GetXinWenListVO;
import com.xinwen.mapper.XinWenMapper;
import com.xinwen.service.XinWenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.xinwen.service.impl
 *
 * @author 庄先生
 * @date 2021/3/7
 */
@Service
public class XinWenServiceImpl implements XinWenService {

    @Autowired
    private XinWenMapper xinWenMapper;

    @Override
    public RResult getXinWenList(RResult result, GetXinWenListParam param) {

        GetXinWenListVO wenListVO = new GetXinWenListVO();

        UpdateWrapper<XinWenEntity> uw = new UpdateWrapper<>();
        if (StringUtils.isNoneBlank(param.getTitle())) {
            uw.like("title", param.getType());
        }
        if (StringUtils.isNoneBlank(param.getType())) {
            uw.eq("type", param.getType());
        }

        Integer count = xinWenMapper.selectCount(uw);
        param.setRecordCount(count);
        if (count > 0) {
            Page<XinWenEntity> page = new Page<>(param.getCurrPage(), param.getPageSize());
            page.setTotal(count);
            Page<XinWenEntity> wenEntityPage = xinWenMapper.selectPage(page, uw);
            wenListVO.setPagelist(wenEntityPage.getRecords());
            wenListVO.setPageparam(param);
        }

        result.changeToTrue(wenListVO);
        return result;
    }

    @Override
    public RResult getXinWenType(RResult result) {
        List<String> xinWenType = xinWenMapper.getXinWenType();
        result.changeToTrue(xinWenType);
        return result;
    }
}
