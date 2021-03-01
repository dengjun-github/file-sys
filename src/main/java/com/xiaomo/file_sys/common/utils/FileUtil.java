package com.xiaomo.file_sys.common.utils;


import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @ClassName: ExcelUtil
 * @Description:
 * @Author: LiuNian
 * @Date: 2020/3/18
 * @Version: v1.0
 */
@Slf4j
public class FileUtil {


    private static final String CONTENT_DISPOSITION = "Content-Disposition";

    public static ResponseEntity<byte[]> downloadFile(byte[] cbyte, String title) {
        try {
            String fileName = URLEncoder.encode(title,"UTF-8");
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            responseHeaders.setContentLength(cbyte.length);
            responseHeaders.set(CONTENT_DISPOSITION, "attachment;filename=" + fileName + ExcelTypeEnum.XLSX.getValue());
            responseHeaders.set("Access-Control-Expose-Headers",CONTENT_DISPOSITION);
            return new ResponseEntity<byte[]>(cbyte, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            log.error("download file error:{}",e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 构建消息头
     * @param response
     * @param fileName
     */
    private static void buildHeader(HttpServletResponse response,String fileName){
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }



}
