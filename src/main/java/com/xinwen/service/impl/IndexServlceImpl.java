package com.xinwen.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xinwen.common.entity.XinWenEntity;
import com.xinwen.common.vo.GetArticleVO;
import com.xinwen.mapper.XinWenMapper;
import com.xinwen.service.IndexServlce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * @author zhuangjl
 * @date 2021/3/11 13:35
 */
@Service
public class IndexServlceImpl implements IndexServlce {

    @Value("${spring.homeUrl:http://localhost:80}")//http://localhost:80
    private String homeUrl;

    @Autowired
    private XinWenMapper xinWenMapper;


    @Override
    @Cacheable(value = "article", key = "#id",condition = "#xinWenEntity == null")
    public ModelAndView getArticle(String id, Model model) {

        //获取文章
        //还有其他一些推荐等
        UpdateWrapper<XinWenEntity> uw = new UpdateWrapper<>();
        uw.eq("content_id", id);
        List<XinWenEntity> wenEntityList = xinWenMapper.selectList(uw);
        if(wenEntityList.size() == 0){
            //跳转到不存在页面
            return new ModelAndView("/error/404");
        }
        XinWenEntity xinWenEntity = wenEntityList.get(0);


        //获取随机新闻
        List<XinWenEntity> suijiXinWen = xinWenMapper.getXinWenRandom(8);

        //获取推荐新闻
        UpdateWrapper<XinWenEntity> uwtuijian = new UpdateWrapper<>();
        uwtuijian.eq("status", 1);
        uwtuijian.orderByDesc("create_time");
        uwtuijian.last("limit 4");
        List<XinWenEntity> tuijian = xinWenMapper.selectList(uwtuijian);

        //获取推荐上一篇和下一篇的链接
        XinWenEntity previous = xinWenMapper.getXinWenPrevious(xinWenEntity.getContentId());
        XinWenEntity next = xinWenMapper.getXinWenNext(xinWenEntity.getContentId());

        model.addAttribute("homeUrl",homeUrl);
        model.addAttribute("previous",previous);
        model.addAttribute("next",next);
        model.addAttribute("tuijian",tuijian);
        model.addAttribute("xw", xinWenEntity);
        model.addAttribute("suijiXinWen", suijiXinWen);
        return new ModelAndView("article", "article", model);
    }

    @Override
    public ModelAndView getIndex(Model model) {
        List<XinWenEntity> xinWenEntities = getTuiJian();
        model.addAttribute("homeUrl",homeUrl);
        model.addAttribute("tuijian",xinWenEntities);
        return new ModelAndView("index", "index", model);
    }

    @Override
    public ModelAndView getSearch(String keyword, String type, Model model) {

        List<XinWenEntity> xinWenEntities = getTuiJian();
        model.addAttribute("tuijian", xinWenEntities);

        model.addAttribute("homeUrl",homeUrl);
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", "两岸".equals(type) ? "国内" : type);
        return new ModelAndView("search", "search", model);
    }


    private List<XinWenEntity> getTuiJian(){
        UpdateWrapper<XinWenEntity> uw = new UpdateWrapper<>();
        uw.eq("status", 1);
        uw.orderByDesc("create_time");
        uw.last("limit 4");

        List<XinWenEntity> xinWenEntities = xinWenMapper.selectList(uw);
        return xinWenEntities;
    }

}
