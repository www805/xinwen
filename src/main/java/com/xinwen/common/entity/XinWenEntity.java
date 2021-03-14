package com.xinwen.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * com.xinwen.common.entity
 *
 * @author 庄先生
 * @date 2021/2/24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("xinwen")
public class XinWenEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1924311439940016867L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键ID

    private Long contentId; //文章ID
    private String title; //文章标题
    private String imgurls; //图片地址
    private String type; //文章类型
    private String content; //文章内容
    private Long viewNum; //文章阅读数量
    private String scheduleTs; //发布时间
    private String lastUpdateDatetime; //最后修改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime; //创建时间

}
