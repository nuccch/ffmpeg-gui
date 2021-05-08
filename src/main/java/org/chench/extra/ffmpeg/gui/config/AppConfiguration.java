package org.chench.extra.ffmpeg.gui.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 应用配置
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.config.AppConfig
 * @date: 4/28/21
 */
@Configuration
public class AppConfiguration {
    @Bean
    MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // 指定文件是UTF-8编码:
        messageSource.setDefaultEncoding("UTF-8");
        // 指定主文件名:
        messageSource.setBasename("messages");
        return messageSource;
    }
}
