package com.xinwen.service;

import com.xinwen.common.param.CrawlAddXinWenParam;
import com.xinwen.common.utils.RResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * com.xinwen.service
 *
 * @author 庄先生
 * @date 2021/2/24
 */
public interface CrawlService {


    RResult addxinwen(CrawlAddXinWenParam param);


    public RResult uploadByImg(MultipartFile file);

}
