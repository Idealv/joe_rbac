package com.joe.rbac.exception;

import lombok.Getter;
import lombok.Setter;

public class BaseException extends RuntimeException {
    @Setter
    @Getter
    private String msg;

    @Setter
    @Getter
    private int code = 500;

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BaseException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BaseException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}
