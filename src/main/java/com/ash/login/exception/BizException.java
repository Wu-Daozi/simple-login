package com.ash.login.exception;

/**
 * 业务异常
 * @author Wu dz
 * @date 2023/4/19
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final int FAILURE = -1;

    private final int errorCode;

    public BizException(String msg) {
        this(msg, FAILURE);
    }

    public BizException(String msg, Throwable throwable) {
        super(msg, throwable);
        this.errorCode = FAILURE;
    }

    public BizException(String msg, Integer errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BizException(String msg, Integer errorCode, Throwable throwable) {
        super(msg, throwable);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
