package org.chench.extra.ffmpeg.gui.ui;

import org.chench.extra.ffmpeg.gui.service.impl.MessageService;

import javax.swing.*;

/**
 * 基础容器类
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.ui.ContainerBase
 * @date: 4/28/21
 */
public abstract class ContainerBase extends JComponent {
    public String getMessage(String key) {
        return MessageService.getInstance().getMessage(key);
    }

    public String getMessage(String key, Object[] args) {
        return MessageService.getInstance().getMessage(key, args);
    }

    public String getVersion() {
        return MessageService.getInstance().getVersion();
    }

    /**
     * 初始化容器自身
     * @return
     */
    protected abstract JComponent init();
}
