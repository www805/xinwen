package com.xinwen.controller;

import com.xinwen.common.entity.XinWenEntity;
import com.xinwen.common.param.GetSearchXinWenParam;
import com.xinwen.common.param.GetXinWenListParam;
import com.xinwen.common.utils.RResult;
import com.xinwen.service.XinWenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * com.xinwen.controller
 *
 * @author 庄先生
 * @date 2021/3/7
 */
@CrossOrigin
@RestController
@RequestMapping("/xinwen")
public class XinWenController {

    @Autowired
    private XinWenService xinWenService;



    /**查询所有新闻*/
    @GetMapping("/getXinWenList")
    public RResult getXinWenList(GetXinWenListParam param) {
        RResult result = new RResult<>();
        return xinWenService.getXinWenList (result, param);
    }

    /**
     * 查询新闻分类接口
     */
    @GetMapping("/getXinWenType")
    public RResult getXinWenType() {
        RResult result = new RResult<>();
        return xinWenService.getXinWenType(result);
    }


    /**
     * 通过id查询新闻接口
     */
    @GetMapping("/getXinWen/{id}")
    public RResult getXinWen(@PathVariable("id") String id) {
        RResult result = new RResult<>();
//        return xinWenService.getXinWen(result);
        return null;
    }

    /**
     * 搜索关键词新闻
     * @param param
     * @return
     */
    @PostMapping("/getSearchXinWen")
    public RResult getSearchXinWen(@RequestBody GetSearchXinWenParam param) {
        RResult result = new RResult<>();
        return xinWenService.getSearchXinWen(result, param);
    }


    /**
     * 修改推荐新闻
     * @param id 文章id
     * @param num 设置推荐1还是取消推荐0
     * @return
     */
    @GetMapping("/updateTuiJianXinWen/{id}/{num}")
    public RResult updateTuiJianXinWen(@PathVariable("id") String id, @PathVariable("num") int num) {
        RResult result = new RResult<>();
        return xinWenService.updateTuiJianXinWen(result, id, num);
    }



}
