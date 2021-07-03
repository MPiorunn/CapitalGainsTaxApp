package com.capital.gains.tax.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

}
