package com.xinwen.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xinwen.common.entity.ImgKuEntity;
import com.xinwen.common.entity.XinWenEntity;
import com.xinwen.common.param.CrawlAddXinWenParam;
import com.xinwen.common.utils.LogUtil;
import com.xinwen.common.utils.OpenUtil;
import com.xinwen.common.utils.RResult;
import com.xinwen.mapper.ImgKuMapper;
import com.xinwen.mapper.XinWenMapper;
import com.xinwen.service.CrawlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * com.xinwen.service.impl
 *
 * @author 庄先生
 * @date 2021/2/24
 */
@Service
public class CrawlServiceImpl implements CrawlService {

    @Value("${spring.images.filePath:D:/uploadImage/}")
    private String filePath;

    @Value("${file.qg:uploadImage}")
    private String houxu;

    @Autowired
    private XinWenMapper xinWenMapper;

    @Autowired
    private ImgKuMapper imgKuMapper;

    @Override
    public RResult addxinwen(CrawlAddXinWenParam param) {

        RResult result = new RResult<>();

        XinWenEntity xinWenEntity = new XinWenEntity();
        try {
            if(StringUtils.isNoneBlank(param.getTitle())){
                String title = URLDecoder.decode(param.getTitle(), "gb2312");
                xinWenEntity.setTitle(title);
            }

            if(StringUtils.isNoneBlank(param.getContent())){
                String content = URLDecoder.decode(param.getContent(), "gb2312");
                xinWenEntity.setContent(content);
            }

            if(StringUtils.isNoneBlank(param.getType())){
                String type = URLDecoder.decode(param.getType(), "gb2312");
                xinWenEntity.setType(type);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        xinWenEntity.setImgurls(param.getImgurls());
        xinWenEntity.setScheduleTs(param.getScheduleTs());
        xinWenEntity.setLastUpdateDatetime(param.getLastUpdateDatetime());
        xinWenEntity.setContentId(param.getContentId());

        UpdateWrapper uw = new UpdateWrapper<>();
        uw.eq("content_id", param.getContentId());
        List<XinWenEntity> xinWenEntities = xinWenMapper.selectList(uw);
        if (xinWenEntities.size() > 0) {
            result.setMessage("该新闻已经存在，无法添加！");
            return result;
        }
        int insert = xinWenMapper.insert(xinWenEntity);

        //int insert = 0;
        if(insert > 0){
            result.changeToTrue();
        }else{
            result.setMessage("新闻添加失败！");
        }

        return result;
    }



    /**
     * 图片上传
     * @param file
     */
    @Override
    public RResult uploadByImg(MultipartFile file) {

        RResult rResult = new RResult<>();

        if (file.isEmpty()) {
            rResult.setMessage("上传失败，请选择文件");
            return rResult;
        }

        //1 获取文件名
        String fileName = file.getOriginalFilename();

        //2 获取随机文件名
        String imageName = OpenUtil.getUUID_32();

        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        imageName += suffix;

        UpdateWrapper ew = new UpdateWrapper<>();
        ew.eq("img_file_name", fileName);

        ImgKuEntity entity = imgKuMapper.selectOne(ew);
        if (null != entity) {
            rResult.setMessage("图片已存在");
            rResult.setData(entity.getImgUrl());
            return rResult;
        }

        String filePathNew = OpenUtil.createpath_fileByBasepath(filePath);

        //3 获取绝对路径
        String realpath = filePathNew + imageName;
        File dest = new File(realpath);
        try {
            file.transferTo(dest);

            //解析下载地址
            String uploadpath=OpenUtil.strMinusBasePath(houxu,realpath);
            rResult.setData(uploadpath);

            //上传的文件保存到数据库表里
            ImgKuEntity imgKuEntity = new ImgKuEntity();
            imgKuEntity.setImgName(imageName);//真实路径文件名
            imgKuEntity.setImgUrl(uploadpath);//下载地址
            imgKuEntity.setImgFileName(fileName);//文件本身的文件名
            imgKuEntity.setImgRecordrealUrl(realpath);//真实存储地址

            int update = imgKuMapper.insert(imgKuEntity);
            if(update > 0){
                rResult.changeToTrue();
                rResult.setMessage("上传成功");
            }else{
                rResult.setMessage("上传失败！");
            }

        } catch (IOException e) {
            rResult.setMessage(e.toString());
        }

        return rResult;
    }


}
