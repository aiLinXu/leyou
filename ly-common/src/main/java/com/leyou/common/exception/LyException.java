package com.leyou.common.exception;

import lombok.Getter;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

@Getter
public class LyException extends RuntimeException {
    private int status;

    public LyException(int status,String message) {
        super(message);
        this.status = status;
    }

    public LyException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}
