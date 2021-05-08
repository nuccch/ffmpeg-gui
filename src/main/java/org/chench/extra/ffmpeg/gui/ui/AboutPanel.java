package org.chench.extra.ffmpeg.gui.ui;

import cn.hutool.core.io.IoUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 关于信息面板
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.ui.AboutPanel
 * @date: 5/8/21
 */
public class AboutPanel extends ContainerBase {
    @Override
    protected JComponent init() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFocusable(false);
        area.setText(loadReadme());
        panel.add(area);
        return panel;
    }

    private String loadReadme() {
        try {
          byte[] bytes = IoUtil.readBytes(AboutPanel.class.getClassLoader().getResourceAsStream("version"));
          return new String(bytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
