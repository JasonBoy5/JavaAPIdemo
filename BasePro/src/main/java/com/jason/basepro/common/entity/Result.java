package com.jason.basepro.common.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;
    private long timestamp;

    public Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(HttpStatus.BAD_REQUEST.value());
        result.setMsg(message);
        return result;
    }

    public static <T> Result<T> fail(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(HttpStatus.BAD_REQUEST.value());
        result.setMsg(message);
        result.setData(data);
        return result;
    }
}
