package com.jason.basepro.common.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "token";
//    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    @Bean
    public Docket getUserDocket(){
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("token").description("user token")//Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());


        return new Docket(DocumentationType.SWAGGER_2)//文档类型（swagger2）
                .enable(true)
                .apiInfo(apiInfo())//设置包含在json ResourceListing响应中的api元信息
                .select()//启动用于api选择的构建器
                .apis(RequestHandlerSelectors.basePackage("com.jason.basepro"))//扫描接口的包,监控所有的包RequestHandlerSelectors.any()
                .paths(PathSelectors.any())//路径过滤器（扫描所有路径）
                .build()
//                .globalOperationParameters(pars);
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()));
    }

    /**
     * @Author: cdl
     * @Description: 生成文档说明
     * @Data: 2021/6/29
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基础代码平台")//api标题
                .description("接口描述")//api描述
                .version("1.0.0")//版本号
                .contact(new Contact("secom","http://192.168.1.40:8801/","654348062@qq.com"))
                .build();
    }


    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION_HEADER , AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
    }
}
