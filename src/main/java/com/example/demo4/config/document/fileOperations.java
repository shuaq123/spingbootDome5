package com.example.demo4.config.document;

import com.example.demo4.config.OSSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Component
public class fileOperations {

    @Autowired
    private OSSConfig ossConfig;
    /***
     * 上传图片
     */
    public HashMap<String,Object> upload(MultipartFile file) {
        HashMap<String,Object> map = new HashMap<>();
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件不能为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        try {
            var s = ossConfig.uploadFile2OSS(file.getInputStream(), fileName);
            map.put("url",s);
            map.put("messgea","上传成功");
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("url"," ");
        map.put("messgea","上传失败");
        return map;
    }
}
