package org.chench.extra.ffmpeg.gui.service.impl;

import org.chench.extra.ffmpeg.gui.service.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * 国际化服务实现
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.service.impl.I18nServiceImpl
 * @date: 4/28/21
 */
@Service
@Qualifier("i18nService")
public class I18nServiceImpl implements I18nService {
    private MessageSource messageSource;
    //private Locale locale = new Locale("en", "US");
    private Locale locale = new Locale("");

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String key) {
        return getMessage(key, null);
    }

    @Override
    public String getMessage(String key, Object[] args) {
        return this.messageSource.getMessage(key, args, locale);
    }
}
