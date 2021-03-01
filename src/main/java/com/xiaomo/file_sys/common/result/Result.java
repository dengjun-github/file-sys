package com.xiaomo.file_sys.common.result;

import lombok.Data;

/**
 * @Description：通用返回对象
 * @author：mc
 * @createDate：2018/9/18
 */
@Data
public class Result<T> implements IResult{
    private static final long serialVersionUID = 874200365941306385L;

    private Integer code;

    private String msg;

    private T data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    Result() {
        setResultCode(ResultCode.SUCCESS);
    }

    Result(T data) {
        setResultCode(ResultCode.SUCCESS);
        this.data = data;
    }

    Result(ResultCode resultCode) {
        setResultCode(resultCode);
    }

    Result(ResultCode resultCode, T data) {
        setResultCode(resultCode);
        this.data = data;
    }

    Result(ErrorResult defaultErrorResult) {
        this.code = defaultErrorResult.getCode();
        this.msg = defaultErrorResult.getMsg();
        this.data = (T)defaultErrorResult.getErrors();
    }

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

}
