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
    private RestTemplate restTemplate;


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



    @GetMapping("/caiji")
    public Object getIndex(){



        String httpNr = doExchange("https://www.881903.com/api/news/recent", 10, "MjAyMS0wMi0yMSAxMDoxMjowMH4yMzc5ODcz");

        return httpNr;
    }



    /**
     * exchange
     * @return
     */
    public String doExchange(String url, Integer pageNum, String offset){
        //header参数
        HttpHeaders headers = new HttpHeaders();
        String token = "asdfaf2322";
        headers.add("authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //放入body中的json参数
        JSONObject obj = new JSONObject();
        obj.put("limit", pageNum);
        obj.put("offset", offset);

        //组装
        HttpEntity<JSONObject> request = new HttpEntity<>(obj, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String body = responseEntity.getBody();
        return body;
    }


}
