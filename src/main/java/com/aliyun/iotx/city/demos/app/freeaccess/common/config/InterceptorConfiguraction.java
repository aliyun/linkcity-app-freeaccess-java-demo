package com.aliyun.iotx.city.demos.app.freeaccess.common.config;

import com.aliyun.iotx.city.demos.app.freeaccess.login.web.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 安悟
 * @Date 2019/5/2 下午5:19
 */
@Configuration
public class InterceptorConfiguraction extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> exclude = new ArrayList<>();
        exclude.add("/css/**");
        exclude.add("/js/**");
        exclude.add("/img/**");
        exclude.add("/fonts/**");
        exclude.add("/favicon.ico");
        exclude.add("/login");
        exclude.add("/logout");
        exclude.add("/thirdpart/**");

        String[] strings = new String[exclude.size()];
        exclude.toArray(strings);

        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(strings);
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry.addResourceHandler("/public*//**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/public/");
        registry.addResourceHandler("/pub*//**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX + "/Users/ywu/temp/pub/");
        super.addResourceHandlers(registry);*/
    }
}
