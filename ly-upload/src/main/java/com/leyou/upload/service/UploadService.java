package com.leyou.upload.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/
@Slf4j
@Service
public class UploadService {

    @Autowired
    private OssProperties prop;

    @Autowired
    private OSS client;


    private static final String images_dir = "D:\\nginx-1.14.0\\html\\images";
    private static final String images_url = "http://image.leyou.com/images/";

    public Object upload(MultipartFile file) {
        //文件的存储地址
        String filename = file.getOriginalFilename();
        //校验文件类型
        String extension= StringUtils.substringAfterLast(filename,".");
        //随机生成一个文件名
        String uuid = UUID.randomUUID().toString();
        filename = uuid+ "." + extension;
        File imagePath = new File(images_dir, filename);

        //保存文件  transferTo：将文件转移到指定的位置上
        try {
            file.transferTo(imagePath);
        } catch (IOException e) {
           log.error("文件上传失败！原因：{}",e.getMessage(),e);
            throw new LyException(ExceptionEnum.FILE_UPLOAD_ERROR,e);
        }

        //返回路径
        return images_url + filename;
    }



    public Map<String, Object> getSignature() {
        try {
            long expireTime = prop.getExpireTime();
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, prop.getMaxFileSize());
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, prop.getDir());

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            Map<String, Object> respMap = new LinkedHashMap<>();
            respMap.put("accessId", prop.getAccessKeyId());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", prop.getDir());
            respMap.put("host", prop.getHost());
            respMap.put("expire", expireEndTime);
            return respMap;
        }catch (Exception e){
            throw new LyException(ExceptionEnum.UPDATE_OPERATION_FAIL);
        }
    }

}

