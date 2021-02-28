package com.xinwen.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@TableName("imgku")
public class ImgKuEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 7642369685783415642L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id; //主键ID

    private String imgUrl; //图片地址
    private String imgName; //图片名称
    private String imgFileName; //图片名称
    private String imgRecordrealUrl; //图片名称
    private Date createTime; //创建时间

}
