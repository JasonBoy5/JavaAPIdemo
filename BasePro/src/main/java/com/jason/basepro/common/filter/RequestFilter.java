package com.jason.basepro.common.filter;



import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.jason.basepro.common.entity.Result;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(1)
@WebFilter
public class RequestFilter implements Filter {

    @Resource
    private GeneralParam generalParam;

    @Resource
    private TokenService tokenService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        long count = generalParam.getNoFilterUrlSet().stream().filter(u -> request.getRequestURI().contains(u)).count();
        if (count == 0) {
            String token = request.getParameter("token");
            if (!StringUtils.hasLength(token)) {
                token = request.getHeader("token");//header方式
            }
            if (!StringUtils.hasLength(token)) {
                Cookie[] cookies = request.getCookies();
                if(null != cookies){
                    for(Cookie cookie : cookies){
                        if(Objects.equals("token",cookie.getName())) token = cookie.getValue();
                    }
                }
            }

            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setContentType("application/json;charset=utf-8");
            if (!StringUtils.hasLength(token)) {
                servletResponse.getWriter().write(JSON.toJSONString(Result.fail(HttpStatus.UNAUTHORIZED.value(), "token不能为空")));
                return;
            }

            if(!tokenService.verifyToken(token)){
                servletResponse.getWriter().write(JSON.toJSONString(Result.fail(HttpStatus.UNAUTHORIZED.value(), "token无效")));
                return;
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
