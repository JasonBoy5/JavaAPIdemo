package com.jason.basepro.common.aop;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jason.basepro.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Comparator;
import java.util.stream.Collectors;

/***
 *@description: 异常结果统一处理
 *@author: wangjf
 *@create: 2021-12-30
 ***/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(Exception e){
        log.error("全局异常信息 ex={}",e.getMessage(),e);
        String errMsg = e.getMessage();
        if(e.getMessage().contains("DateTimeParseException")){
            errMsg = "时间格式错误";
        }
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), errMsg);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> httpMessageNotReadableException(Exception e){
        log.error("入参不合法->{}",e.getMessage());
        return Result.fail(HttpStatus.BAD_REQUEST.value(),"入参不合法");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Result<String> duplicateKeyException(Exception e){
        log.error("数据库写入异常 ex={}",e.getMessage(),e);
        String field = e.getCause().toString().split("'")[3].toString().split("_")[1];
        String fieldStr = "其它字段";
        if(field.contains("id")){
            fieldStr = "id";
        }else if(field.contains("number")){
            fieldStr = "编码";
        }
        String msg = fieldStr + "冲突";
        return Result.fail(HttpStatus.CONFLICT.value(), msg);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> nullPointException(Exception e){
        log.error("空指针异常 ex={}",e.getMessage(),e);
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    //swagger 参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        String msg = bindingResult.getFieldErrors().stream().sorted(Comparator.comparing(FieldError::getField))
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        log.error("参数验证失败: {},", e.getMessage());
        return Result.fail(HttpStatus.BAD_REQUEST.value(), msg);
    }

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handleJWTVerificationException(JWTVerificationException e){
        return Result.fail(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

}
