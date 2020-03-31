package com.xblog.configurer;

import com.xblog.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WebConfiguration
 * @Description: 支持跨域请求 / token拦截
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Value("${xblog.upload.dir}")
    private String UPLOAD_DIR;

    @Value("${xblog.upload.url}")
    private String UPLOAD_URL;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/register");  //注册
        excludePath.add("/login");     //登录
        excludePath.add("/swagger-resources/**");
        excludePath.add("/webjars/**");
        excludePath.add("/v2/**");
        excludePath.add("/csrf");
        excludePath.add("/swagger-ui.html/**");
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns(excludePath)
                .excludePathPatterns("/upload/**") // 排除图片请求签名
                .addPathPatterns("/**");
    }

    //配置静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(UPLOAD_DIR + "/**").addResourceLocations("file:" + UPLOAD_DIR + "/");
    }
}
