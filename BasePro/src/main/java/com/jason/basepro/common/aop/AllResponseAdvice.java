package com.jason.basepro.common.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.basepro.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})//添加该注解避免无法访问swagger页面
public class AllResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //springfox.documentation.swagger.web.ApiResourceController
        return !methodParameter.getDeclaringClass().getName().contains("springfox");//不拦截swagger页面
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof String) {
            ObjectMapper om = new ObjectMapper();
            try {
                return om.writeValueAsString(Result.success(o));
            } catch (JsonProcessingException e) {
                log.error("序列化错误:{}", e.getMessage(), e);
                return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "错误");
            }
        } else if (o instanceof Result)
            return o;
        else if (o instanceof Void)
            return Result.success(null);
        else if (o instanceof Integer) {
            return Result.success(o);
        } else
            return Result.success(o);
    }
}