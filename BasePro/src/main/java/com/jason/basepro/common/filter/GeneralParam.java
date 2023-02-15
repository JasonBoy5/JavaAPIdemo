package com.jason.basepro.common.filter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Data
@ConfigurationProperties(prefix = "general")
@Configuration
public class GeneralParam {
    private Set<String> noFilterUrlSet;
}
