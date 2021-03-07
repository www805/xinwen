package com.xinwen.controller;

import com.alibaba.fastjson.JSONObject;
import com.xinwen.common.param.CrawlAddXinWenParam;
import com.xinwen.common.utils.RResult;
import com.xinwen.service.CrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * com.xinwen.controller
 *
 * @author 庄先生
 * @date 2021/2/21
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/crawl")
public class CrawlController {



    @Autowired
    private CrawlService crawlService;



    @PostMapping("/addxinwen")
    public RResult addxinwen(@RequestBody CrawlAddXinWenParam param){
        return crawlService.addxinwen(param);
    }


    /***
     * 图片上传接口
     * @return
     */
    @PostMapping(value = "/uploadImg")
    public RResult uploadByClientImg(@RequestParam("file") MultipartFile file) {
        return crawlService.uploadByImg(file);
    }


}
