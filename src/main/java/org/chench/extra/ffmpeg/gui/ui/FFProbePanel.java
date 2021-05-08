package org.chench.extra.ffmpeg.gui.ui;

import javax.swing.*;
import java.awt.*;

/**
 * ffprobe命令面板
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.ui.FFProbePanel
 * @date: 4/28/21
 */
public class FFProbePanel extends ContainerBase {

    @Override
    public JComponent init() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTabbedPane tab = new JTabbedPane(SwingConstants.LEFT);
        tab.addTab(getMessage("title.show.streams"), initFFProbeShowStreamPanel());
        //tab.addTab("查看格式", initFFProbeShowFormatPanel());
        panel.add(tab);
        return panel;
    }

    private Component initFFProbeShowStreamPanel() {
        FFProbeShowStreamPanel panel = new FFProbeShowStreamPanel();
        return panel.init();
    }

    private Component initFFProbeShowFormatPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("查看媒体格式"));
        return panel;
    }
}
