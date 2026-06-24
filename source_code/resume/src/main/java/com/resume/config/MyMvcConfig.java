package com.resume.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//扩展MVC,添加注解变成配置类
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //配置视图
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("index");
        registry.addViewController("/admin.html").setViewName("admin"); // 添加admin视图
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截一些请求，有些不能拦截
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login.html","/","/user/login","/index","/login","/css/**","/js/**","/img/**","/export-word");
    }
}
