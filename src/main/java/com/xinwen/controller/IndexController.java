package com.xinwen.controller;

import com.xinwen.common.vo.GetArticleVO;
import com.xinwen.service.IndexServlce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * com.xinwen.controller
 *
 * @author 庄先生
 * @date 2021/3/8
 */
@Controller
public class IndexController {

    @Autowired
    private IndexServlce indexServlce;


    @RequestMapping({"/", "index.html"})
    public ModelAndView getIndex(Model model) {
        return new ModelAndView("index", "index", model);
    }

    //独立新闻页面
    @RequestMapping("/article/{id}.html")
    public ModelAndView getArticle(@PathVariable("id") String id, Model model) {
        GetArticleVO articleVO = indexServlce.getArticle(id);
        model.addAttribute(articleVO);
        return new ModelAndView("article", "article", model);
    }

    @RequestMapping("/search/{keyword}.html")
    public ModelAndView getSearch(@PathVariable("keyword") String keyword, Model model) {

        return new ModelAndView("search", "search", model);
    }






}
