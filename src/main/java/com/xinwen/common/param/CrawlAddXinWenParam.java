package com.xinwen.common.param;

import lombok.Data;

import java.util.Date;

/**
 * com.xinwen.common.param
 *
 * @author 庄先生
 * @date 2021/2/27
 */
@Data
public class CrawlAddXinWenParam {

    private Long contentId; //文章ID
    private String title; //文章标题
    private String imgurls; //图片地址
    private String type; //文章类型
    private String content; //文章内容
    private String scheduleTs; //发布时间
    private String lastUpdateDatetime; //最后修改时间
    private String descriptionNr; //文章描述

}
