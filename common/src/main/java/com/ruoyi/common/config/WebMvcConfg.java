/*
 * FileName：WebMvcConfg.java 
 * <p>
 * Copyright (c) 2017-2020 <a href="https://www.smartwx.info">hermit(1154808491@qq.com)</a>.
 * <p>
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl-3.0.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.ruoyi.common.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    /**
     * 本地文件路径
     */
    public static final String localFilePath="file:%s";


    //@Value("${res.upload.path}")
    @Value("${ruoyi.profile}")
    private String res_upload_path;

    /**
     * 增加utf-8乱码
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> encodingFilterRegistration() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        FilterRegistrationBean<CharacterEncodingFilter> registration = new FilterRegistrationBean<CharacterEncodingFilter>(
                encodingFilter);
        registration.setName("encodingFilter");
        registration.addUrlPatterns("/*");
        registration.setAsyncSupported(true);
        registration.setOrder(1);
        return registration;
    }





    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/upload/");
        registry.addResourceHandler("/temporary/**").addResourceLocations(String.format(localFilePath,res_upload_path));
        registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/", "classpath:/META-INF/resources/",
                "classpath:/resources/", "classpath:/static/", "classpath:/public/", "classpath:/views/");
    }
}
