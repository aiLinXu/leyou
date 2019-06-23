package com.leyou.common.advice;

import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ExceptionPoJo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

@ControllerAdvice
public class BasicExceptionAdvice {

    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionPoJo> handleException(LyException e){
        e.printStackTrace();
        return ResponseEntity.status(e.getStatus()).body(new ExceptionPoJo(e));
    }
}
