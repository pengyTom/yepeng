package com.zzh.service;


import com.zzh.common.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 主要做文件上传的配置信息
 */
public interface PictureService {
	public PictureResult uploadFile(MultipartFile uploadFile) throws Exception;
}
