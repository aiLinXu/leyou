package com.leyou.common.vo;

import com.leyou.common.exception.LyException;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

@Data
public class ExceptionPoJo {
    private int status;
    private String message;
    private String timestamp;

    public ExceptionPoJo(LyException e) {
        this.status =e.getStatus();
        this.message =e.getMessage();
        this.timestamp = new Date().toLocaleString();
    }
}
