package com.chatbb.myconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebStaticConfig implements WebMvcConfigurer {
    @Value("${file-save-path}")
    private String fileSavePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 配置资源映射
         * 意思是：如果访问的资源路径是以“/static/”开头的，
         * 就给我映射到本机的“D:/IDEAWork/Vue/static”这个文件夹内，去找你要的资源
         * 注意：:/static/ 后面的 “/”一定要带上
         */
        registry.addResourceHandler("/static/**").addResourceLocations("file:"+fileSavePath);
    }
}
