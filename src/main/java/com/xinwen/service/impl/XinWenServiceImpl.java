package com.xinwen.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinwen.common.entity.XinWenEntity;
import com.xinwen.common.param.GetSearchXinWenParam;
import com.xinwen.common.param.GetXinWenListParam;
import com.xinwen.common.utils.RResult;
import com.xinwen.mapper.XinWenMapper;
import com.xinwen.service.XinWenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

        UpdateWrapper<XinWenEntity> uw = new UpdateWrapper<>();
        if (StringUtils.isNoneBlank(param.getTitle())) {
            uw.like("title", param.getType());
        }
        if (StringUtils.isNoneBlank(param.getType())) {
            uw.eq("type", param.getType());
        }
        uw.orderByDesc("last_update_datetime");

        Integer count = xinWenMapper.selectCount(uw);
        if (count > 0) {
            Page<XinWenEntity> page = new Page<>(param.getCurrPage(), param.getPageSize());
            page.setTotal(count);
            Page<XinWenEntity> wenEntityPage = xinWenMapper.selectPage(page, uw);
            result.changeToTrue(wenEntityPage);
        }

        return result;
    }

    @Override
    public RResult getXinWenType(RResult result) {
        List<String> xinWenType = xinWenMapper.getXinWenType();
        result.changeToTrue(xinWenType);
        return result;
    }

    @Override
    public RResult getSearchXinWen(RResult result, GetSearchXinWenParam param) {
        //关键字和类型同时为空的时候，就返回这个，如果其中有一个不为空，就按照传入的值去查询
        if (StringUtils.isBlank(param.getKeyword()) && StringUtils.isBlank(param.getType())) {
            result.setMessage("关键字不能为空！");
            return result;
        }

        UpdateWrapper<XinWenEntity> uw = new UpdateWrapper<>();
        try {
            String keyword = param.getKeyword();
            String type = param.getType();

            if (StringUtils.isNoneBlank(keyword)) {
                keyword = URLDecoder.decode(keyword, "UTF-8");
                uw.like("title", keyword);
            }
            if (StringUtils.isNoneBlank(type)) {
                type = URLDecoder.decode(type, "UTF-8");
                uw.eq("type", type);
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        uw.orderByDesc("last_update_datetime");

        Integer count = xinWenMapper.selectCount(uw);
        if (count > 0) {
            Page<XinWenEntity> page = new Page<>(param.getCurrPage(), param.getPageSize());
            page.setTotal(count);
            Page<XinWenEntity> searchXinWens = xinWenMapper.selectPage(page, uw);
            result.changeToTrue(searchXinWens);
        }

        return result;
    }
}
