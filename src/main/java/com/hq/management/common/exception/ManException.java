package com.hq.management.common.exception;

/**
 * @program: management
 * @description:
 * @author: Mr.Huang
 * @create: 2018-10-30 09:41
 **/
public class ManException extends RuntimeException {
    private Integer code;
    private String message;

    public ManException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
