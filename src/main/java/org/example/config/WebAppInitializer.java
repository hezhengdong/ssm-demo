package org.example.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 根容器配置（Service、Mapper、数据源等）
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    // Web 容器配置（Controller、拦截器等）
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // DispatcherServlet 映射路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};  // 拦截所有请求
    }
}
