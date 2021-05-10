package org.chench.extra.ffmpeg.gui.ui;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;

/**
 * 主窗口
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.ui.MainFrame
 * @date: 4/28/21
 */
public class MainFrame extends ContainerBase {
    /** 窗口默认宽度 */
    private static final int WIDTH = 1204;
    /** 窗口默认高度 */
    private static final int HEIGHT = 768;

    @Override
    public JComponent init() {
        initMainFrame();
        return null;
    }

    private void initMainFrame() {
        // You should always work with UI inside Event Dispatch Thread (EDT)
        // That includes installing L&F, creating any Swing components etc.
        SwingUtilities.invokeLater (() -> {
            try {
                String osName = System.getProperty("os.name").toLowerCase();
                if (osName.contains("windows")) {
                    // https://github.com/JackJiang2011/beautyeye
                    //org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
                    UIManager.put("RootPane.setupButtonVisible", false);
                    //设置本属性将改变窗口边框样式定义
                    BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
                } else {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                }

                //UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");

                // Install WebLaF as application LaF
                //WebLookAndFeel.install ();

                // You can also specify preferred skin right-away
                // WebLookAndFeel.install ( WebDarkSkin.class );

                // You can also do that in one of the old-fashioned ways
                // UIManager.setLookAndFeel ( new WebLookAndFeel() );
                // UIManager.setLookAndFeel ( "com.alee.laf.WebLookAndFeel" );
                // UIManager.setLookAndFeel ( WebLookAndFeel.class.getCanonicalName () );

                // You can also configure other WebLaF managers as you like now
                // StyleManager
                // SettingsManager
                // LanguageManager
                // ...

                // Initialize your application once you're done setting everything up
                // JFrame frame = ...

                // You can also use Web* components to get access to some extended WebLaF features
                // WebFrame frame = ...

                JFrame mainFrame = new JFrame();
                String title = String.format("%s %s", getMessage("title.main"), getVersion());
                mainFrame.setTitle(title);
                mainFrame.setSize(WIDTH, HEIGHT);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.getContentPane().add(initMainTab());
                mainFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private Component initMainTab() {
        JTabbedPane tab =new JTabbedPane();
        ImageIcon icon= new ImageIcon("temp.gif");
        tab.addTab(getMessage("title.ffprobe"), icon, initFFProbePanel(), getMessage("title.ffprobe").toLowerCase());
        //tab.addTab(getMessage("title.ffmpeg"), icon, iniFFMpegPanel(), getMessage("title.ffmpeg").toLowerCase());
        tab.addTab(getMessage("title.about"), icon, initAboutPanel(), getMessage("title.about").toLowerCase());
        //tab.setMnemonicAt(0, KeyEvent.VK_1);
        //tab.setMnemonicAt(1, KeyEvent.VK_2);
        //tab.setMnemonicAt(2, KeyEvent.VK_3);
        return tab;
    }

    /**
     * 初始化ffprobe命令面板
     * @return
     */
    private JComponent initFFProbePanel() {
        FFProbePanel panel = new FFProbePanel();
        return panel.init();
    }

    /**
     * 初始化ffmpeg命令面板
     * @return
     */
    private JComponent iniFFMpegPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("ffmpeg命令"));
        return panel;
    }

    /**
     * 初始化关于信息面板
     * @return
     */
    private JComponent initAboutPanel() {
        AboutPanel panel = new AboutPanel();
        return panel.init();
    }
}
