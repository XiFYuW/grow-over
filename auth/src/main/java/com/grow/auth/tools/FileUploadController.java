package com.grow.auth.tools;

import com.grow.auth.security.annotation.AnonymousAccess;
import com.grow.common.file.service.FileUploadService;
import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@Api(tags = "文件")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @ApiOperation(value="文件上传")
    @PostMapping(value = "/fileUpload", produces = "application/json;charset=UTF-8", headers = "content-type=multipart/form-data")
    @AnonymousAccess
    @LogOutAnnotation(url = "/fileUpload", request = false)
    @ApiImplicitParam(name = "file", value="文件对象，多个以数组", dataType="__file", paramType = "form")
    public ResponseResult importToExcel(@RequestParam(name = "file") MultipartFile[] file) throws Exception {
        return fileUploadService.fileUpload(file);
    }
}
