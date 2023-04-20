package com.ash.login.config;

import com.ash.login.filter.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置
 * @author Wu dz
 * @date 2023/4/19
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 拦截器
     * addPathPatterns()方法中配置的路由都会被拦截
     * 例如 "/system"、"/bbb"开始的路由都会被拦截
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/system/**", "/bbb/**");
    }
}
