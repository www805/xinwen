package com.xinwen.service;

import com.xinwen.common.vo.GetArticleVO;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @author zhuangjl
 * @date 2021/3/11 13:35
 */
public interface IndexServlce {


    void getArticle(String id, Model model);


}
