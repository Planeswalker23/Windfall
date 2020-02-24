package org.planeswalker.controller;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Response;
import org.planeswalker.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传
 * @author Planeswalker23
 * @date Created in 2020/2/24
 */
@Slf4j
@RestController
public class FileController {

    @Autowired
    private UserMapper userMapper;
    /**
     * yml配置文件中配置的文件上传路径
     */
    @Value("${file.upload-path}")
    private String filePath;

    /**
     * 上传文件
     * @param file
     * @return String 文件路径
     * @throws IOException
     */
    @PostMapping("/upload")
    public Response<String> fileUpload(MultipartFile file) throws IOException {
        // 上传文件校验
        if (file.isEmpty()) {
            return Response.failed("上传的文件为空");
        }
        String fileName = file.getOriginalFilename();
        String path = filePath + fileName;
        File uploadingFile = new File(path);
        // 检测路径是否存在，若不存在新建文件夹
        if (!uploadingFile.getParentFile().exists()) {
            uploadingFile.getParentFile().mkdirs();
        }
        // 写入文件
        file.transferTo(uploadingFile);
        log.info("文件: {} 上传成功", fileName);
        log.info("上传路径为: {}", filePath);
        return Response.success(path);
    }
}
