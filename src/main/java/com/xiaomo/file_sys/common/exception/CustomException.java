package com.xiaomo.file_sys.common.exception;


import com.xiaomo.file_sys.common.result.ResultCode;

/**
 * @Description 远程调用异常
 * @Author mc
 * @Date 2019/12/26
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 193906846739586856L;

    private Integer code;

    private String msg;

    private ResultCode resultCode;

    private Object data;

    public CustomException() {

    }
    public CustomException(String msg) {
        this.msg = msg;
        this.code = 999;
    }

    public CustomException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
