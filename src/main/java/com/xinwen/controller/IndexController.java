package com.xinwen.controller;

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

    @RequestMapping({"/","index"})
    public ModelAndView getIndex(Model model){
        return new ModelAndView("index", "index", model);
    }

    @RequestMapping("/article/{id}")
    public ModelAndView getArticle(@PathVariable("id") String id,Model model){
        System.out.println(id);
        return new ModelAndView("article", "article", model);
    }

    @RequestMapping("search")
    public ModelAndView getSearch(Model model){
        return new ModelAndView("search", "search", model);
    }

}
