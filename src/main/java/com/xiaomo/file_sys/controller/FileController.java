package com.xiaomo.file_sys.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.xiaomo.file_sys.common.factory.PageFactory;
import com.xiaomo.file_sys.common.result.Result;
import com.xiaomo.file_sys.common.result.ResultWrap;
import com.xiaomo.file_sys.module.FileInfo;
import com.xiaomo.file_sys.service.FileService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author DJ
 * @className FileController
 * @Description
 * @date 2021-02-25 14:17
 */
@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@ApiOperation("上传图片")
	@PostMapping("/upload/img")
	public Result<String> uploadImage(@RequestParam("file") MultipartFile file){
		return ResultWrap.success(fileService.uploadImage(file));
	}

	@ApiOperation("获取上传记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageSize", value = "每页条数", required = true),
			@ApiImplicitParam(name = "pageNum", value = "第几页", required = true),
			@ApiImplicitParam(name = "sort", value = "排序字段"),
			@ApiImplicitParam(name = "order", value = "升/降 ascending升 descending降", defaultValue = "ascending")
	})
	@GetMapping("/upload/list")
	public Result<Page<FileInfo>> getUploadRecordList() {
		Page<FileInfo> page = new PageFactory<FileInfo>().defaultPage();
		return ResultWrap.success(fileService.listUploadRecode(page));
	}

	@ApiOperation("下载文件")
	@GetMapping("download/img/{id}")
	public void downloadFile(@PathVariable("id") Long id) {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = ((ServletRequestAttributes) attributes).getResponse();
		fileService.downloadFile(id,response);
	}
}
