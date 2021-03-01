package com.xiaomo.file_sys.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaomo.file_sys.common.exception.CustomException;
import com.xiaomo.file_sys.mapper.FileInfoMapper;
import com.xiaomo.file_sys.module.FileInfo;
import com.xiaomo.file_sys.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;

/**
 * @author DJ
 * @className FileServiceImpl
 * @Description
 * @date 2021-02-25 14:28
 */
@Service
@Slf4j
public class FileServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileService {
	@Value("${sys.file-upload-path}")
	private String parentPath;

	@Value("${sys.host}")
	private String host;

	@Autowired
	private FileInfoMapper fileInfoMapper;

	@Override
	public String uploadImage(MultipartFile file) {
		String realName = file.getOriginalFilename();
		//生成UUID文件名
		String fileName = UUID.fastUUID().toString();
		if (StrUtil.isNotBlank(realName)) {
			String suffix = realName.substring(realName.lastIndexOf(".") + 1);
			fileName += "." + suffix;
		}
		String filePath = parentPath + System.getProperty("file.separator") + getMonthPath();
		filePath += System.getProperty("file.separator") + fileName;
		File desFile = new File(filePath);
		if (!desFile.getParentFile().exists()) {//自动创建路径
			boolean mkdirs = desFile.getParentFile().mkdirs();
			if (!mkdirs) {
				log.error("文件自动创建路径:{}", filePath);
			}
		}

		//保存本地
		try {
			file.transferTo(desFile);
		} catch (IOException e) {
			log.error("上传图片失败，文件路径:{}", filePath);
			throw new CustomException("上传图片失败");
		}
		log.info("上传图片成功，文件路径:{}", filePath);
		String realPath = filePath;
		filePath = host+"/"+filePath.replace(parentPath,"uploads").replace(System.getProperty("file.separator"),"/");

		fileInfoMapper.insert(FileInfo.builder()
				.createTime(System.currentTimeMillis())
				.filename(realName)
				.fileReqPath(filePath)
				.fileRealPath(realPath)
				.build());
		return filePath;
	}

	@Override
	public Page<FileInfo> listUploadRecode(Page<FileInfo> page) {
		return this.selectPage(page);
	}

	@Override
	public void downloadFile(Long id, HttpServletResponse response) {
		FileInfo fileInfo = fileInfoMapper.selectById(id);
		File file = new File(fileInfo.getFileRealPath());

		// 取得文件名。
		String filename = fileInfo.getFilename();
		OutputStream toClient = null;
		try {
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(fileInfo.getFileRealPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
			response.addHeader("Content-Length", "" + file.length());
			toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
		} catch (Exception e) {
			log.error("下载文件失败,cause={},message={}",e.getCause(),e.getMessage());
		} finally {
			try {
				assert toClient != null;
				toClient.close();
			} catch (IOException ignored) {

			}
		}
	}

	private String getMonthPath() {
		Calendar calendar = Calendar.getInstance();
		String year = calendar.get(Calendar.YEAR) + "";
		String month = (calendar.get(Calendar.MONTH) + 1) + "";
		return year + System.getProperty("file.separator") + month;
	}
}
