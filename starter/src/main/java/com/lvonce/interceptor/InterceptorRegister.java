package com.lvonce.interceptor;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class InterceptorRegister implements WebMvcConfigurer {

    private SqlRunnerInterceptor runnerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(runnerInterceptor)
                .addPathPatterns("/api/**"); // 拦截所有 /api 下的请求
//                .excludePathPatterns("/api/exclude"); // 排除某些路径
    }
}
