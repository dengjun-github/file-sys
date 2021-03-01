package com.xiaomo.file_sys.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xiaomo.file_sys.module.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author DJ
 * @className FileService
 * @Description
 * @date 2021-02-25 14:18
 */
public interface FileService extends IService<FileInfo> {

	/**
	 * 上传图片
	 * @param file
	 * @return
	 */
	String uploadImage(MultipartFile file);

	/**
	 * 获取文件上传记录
	 * @param page
	 * @return
	 */
	Page<FileInfo> listUploadRecode(Page<FileInfo> page);

	void downloadFile(Long id, HttpServletResponse response);

}
