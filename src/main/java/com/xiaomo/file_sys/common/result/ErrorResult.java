package com.xiaomo.file_sys.common.result;

import cn.hutool.core.util.StrUtil;
import com.xiaomo.file_sys.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

public class ErrorResult implements IResult{
    private static final long serialVersionUID = 1899083570489722793L;

    /**
     * HTTP响应状态码 {@link HttpStatus}
     */
    private Integer status;

    /**
     * HTTP响应状态码的英文提示
     */
    private String error;

    /**
     * 异常堆栈的精简信息
     *
     */
    private String msg;

    /**
     * 我们系统内部自定义的返回值编码，{@link ResultCode} 它是对错误更加详细的编码
     *
     * 备注：spring boot默认返回异常时，该字段为null
     */
    private Integer code;

    /**
     * 调用接口路径
     */
    private String path;

    /**
     * 异常的名字
     */
    private String exception;

    /**
     * 异常的错误传递的数据
     */
    private Object errors;

    /**
     * 时间戳
     */
    private Date timestamp;

    public static ErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus, Object errors) {
        ErrorResult result = ErrorResult.failure(resultCode, e, httpStatus);
        result.setErrors(errors);
        return result;
    }

    public static ErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus) {

        ErrorResult result = new ErrorResult();
        if(resultCode != null){
            result.setCode(resultCode.code());
            result.setMsg(resultCode.message());
        }else{
            if(e instanceof CustomException){
                result.setCode(((CustomException) e).getCode());
                result.setMsg(((CustomException) e).getMsg());
            }
        }
        result.setStatus(httpStatus.value());
        result.setError(httpStatus.getReasonPhrase());
        result.setException(e.getClass().getName());
        String path = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getRequestURI();
        result.setPath(path);
        result.setTimestamp(new Date());
        return result;
    }


    public static ErrorResult failure(CustomException e) {
        ErrorResult defaultErrorResult = ErrorResult.failure(e.getResultCode(), e, HttpStatus.OK, e.getData());
        if (StrUtil.isNotEmpty(e.getMessage())) {
            defaultErrorResult.setMsg(e.getMessage());
        }
        return defaultErrorResult;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
