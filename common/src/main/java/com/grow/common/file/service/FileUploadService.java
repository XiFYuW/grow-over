package com.grow.common.file.service;

import com.grow.common.result.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/01 9:05
 */
public interface FileUploadService {
    /**
     * 文件上传
     */
    ResponseResult fileUpload(MultipartFile[] file) throws Exception;

    ResponseResult base64FileUpload(String base64File, String originalFilename) throws Exception;
}
