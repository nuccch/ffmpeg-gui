package org.chench.extra.ffmpeg.gui.service;

/**
 * 国际化服务接口
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.service.I18nService
 * @date: 4/28/21
 */
public interface I18nService {
    String getMessage(String key);
    String getMessage(String key, Object[] args);
}
