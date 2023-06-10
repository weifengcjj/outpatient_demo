package com.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("---------------getRootConfigClasses");
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("------------------getServletConfigClasses");
        //配置一些拦截器，和视图解析器
        return new Class[]{ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        //拦截所有请求;
        System.out.println("-------------getServletMappings");
        return new String[]{"/"};
    }
}
