package org.chench.extra.ffmpeg.gui.service.impl;

import cn.hutool.core.io.IoUtil;
import org.chench.extra.ffmpeg.gui.service.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.service.impl.MessageService
 * @date: 4/28/21
 */
@Service
public class MessageService {
    private static MessageService instance = null;
    private I18nService i18nService;
    private String version;

    public MessageService() {
        instance = this;
        this.version = readVersion();
    }

    private String readVersion() {
        String line = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("version")));
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(reader);
        }
        return line;
    }

    public static MessageService getInstance() {
        return instance;
    }

    public String getMessage(String key) {
        return this.i18nService.getMessage(key);
    }

    public String getMessage(String key, Object[] args) {
        return this.i18nService.getMessage(key, args);
    }

    @Autowired
    public void setI18nService(I18nService i18nService) {
        this.i18nService = i18nService;
    }

    public String getVersion() {
        return version;
    }
}
