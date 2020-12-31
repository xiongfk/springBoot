package com.xiongfk.springBooting.exception;

import com.xiongfk.springBooting.exception.service.BaseExceptionService;

/**
 * 功能描述 TODO
 * 自定义一个异常类，用于处理我们发生的业务异常
 * @Author xiongfk
 * @Date 2020/4/30
 * @Version 1.0
 **/
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BizException() {
        super();
    }

    public BizException(BaseExceptionService baseExceptionService) {
        super(baseExceptionService.getResultCode());
        this.errorCode = baseExceptionService.getResultCode();
        this.errorMsg = baseExceptionService.getResultMsg();
    }

    public BizException(BaseExceptionService baseExceptionService, Throwable cause) {
        super(baseExceptionService.getResultCode(), cause);
        this.errorCode = baseExceptionService.getResultCode();
        this.errorMsg = baseExceptionService.getResultMsg();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMessage() {
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
