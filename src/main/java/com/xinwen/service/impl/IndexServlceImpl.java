package com.xinwen.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xinwen.common.entity.XinWenEntity;
import com.xinwen.common.vo.GetArticleVO;
import com.xinwen.mapper.XinWenMapper;
import com.xinwen.service.IndexServlce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * @author zhuangjl
 * @date 2021/3/11 13:35
 */
@Service
public class IndexServlceImpl implements IndexServlce {


    @Autowired
    private XinWenMapper xinWenMapper;

    @Override
    public void getArticle(String id, Model model) {

        GetArticleVO articleVO = new GetArticleVO();

        //获取文章
        //还有其他一些推荐等
        UpdateWrapper<XinWenEntity> uw = new UpdateWrapper<>();
        uw.eq("content_id", id);
        List<XinWenEntity> wenEntityList = xinWenMapper.selectList(uw);
        if(wenEntityList.size() == 0){
            //跳转到不存在页面
            RedirectView redirectView = new RedirectView("baidu.com");
            model.addAttribute(redirectView);
            return;
        }
        XinWenEntity xinWenEntity = wenEntityList.get(0);

        articleVO.setXinWenEntity(xinWenEntity);

        model.addAttribute(articleVO);
    }
}
