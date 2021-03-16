package com.xinwen.service;

import com.xinwen.common.vo.GetArticleVO;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author zhuangjl
 * @date 2021/3/11 13:35
 */
public interface IndexServlce {


    ModelAndView getArticle(String id, Model model);


    ModelAndView getIndex(Model model);

    ModelAndView getSearch(String keyword, String type, Model model);

}
