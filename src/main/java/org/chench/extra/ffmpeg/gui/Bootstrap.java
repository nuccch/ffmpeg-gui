package org.chench.extra.ffmpeg.gui;

import org.chench.extra.ffmpeg.gui.service.I18nService;
import org.chench.extra.ffmpeg.gui.ui.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 程序启动类
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.Bootstrap
 * @date: 4/28/21
 */
@Component
public class Bootstrap {
    private I18nService i18nService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(Bootstrap.class.getPackage().getName());
        context.refresh();
        context.start();
        context.getBean(Bootstrap.class).start();
    }

    @Autowired
    public void setI18nService(I18nService i18nService) {
        this.i18nService = i18nService;
    }

    private void start() {
        new MainFrame().init();
    }
}
