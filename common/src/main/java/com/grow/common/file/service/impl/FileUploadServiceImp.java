package com.grow.common.file.service.impl;

import cn.hutool.core.date.DateUtil;
import com.grow.common.file.FileUtil;
import com.grow.common.file.FileVO.FileVo;
import com.grow.common.file.service.FileUploadService;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import com.grow.config.file.FileConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/01 9:05
 */
@Service
@Slf4j
public class FileUploadServiceImp implements FileUploadService {

    private final FileConfig fileConfig;

    public FileUploadServiceImp(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    @Override
    public ResponseResult fileUpload(MultipartFile[] file) throws Exception {
        List<FileVo> data = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            String originalFilename = multipartFile.getOriginalFilename();
            if(!(multipartFile.getSize() == 0L && "".equals(originalFilename))) {
                String fileName = FileUtil.upload(multipartFile, fileConfig.getUploadFolder());
                FileVo fileVo = getFileVo(fileName, originalFilename);
                data.add(fileVo);
            }
        }
        return ResponseResultUtils.getResponseResultS("文件上传成功", data);
    }

    @Override
    public ResponseResult base64FileUpload(String base64File, String originalFilename) throws Exception {
        String fileName = FileUtil.uploadByBase64(base64File, fileConfig.getUploadFolder());
        if (StringUtils.isEmpty(fileName)) {
            ResponseResultUtils.getResponseResultF("base64文件错误");
        }
        FileVo fileVo = getFileVo(fileName, originalFilename);
        return ResponseResultUtils.getResponseResultS("文件上传成功", fileVo);
    }

    private FileVo getFileVo(String fileName, String originalFilename) throws UnsupportedEncodingException {
        final FileVo fileVo = new FileVo();
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(fileConfig.getUrlFolder())
                .path(fileName)
                .toUriString();
        fileDownloadUri = URLDecoder.decode(fileDownloadUri.replaceAll("%5C", "%2F"), "utf-8");
        log.info(originalFilename + "文件访问地址：【{}】", fileDownloadUri);
        fileVo.setUrl(fileDownloadUri);
        fileVo.setCurrentTimeMillis(DateUtil.current(false));
        fileVo.setTitle(fileName);
        fileVo.setSourceTitle(originalFilename);
        return fileVo;
    }
}
