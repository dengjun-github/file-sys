package com.xiaomo.file_sys.common.result;


public class ResultWrap {

	private ResultWrap() {
	}


	public static <E> Result<E> wrap(ResultCode code, E o) {
		return new Result<>(code, o);
	}

	public static <E> Result<E> success() {
		return new Result<>();
	}

    public static <E> Result<E> success(E o) {
        return new Result<>(o);
    }

	public static <E> Result<E> fail(E o) {
		return new Result<>(ResultCode.FAIL,o);
	}

	public static <E> Result<E> fail() {
		return new Result<>(ResultCode.FAIL);
	}

	public static <E> Result<E> error(ErrorResult o) {
		return new Result<>(o);
	}

	public static <E> Result<E> error() {
		return new Result<>(ResultCode.ERROR);
	}
}
