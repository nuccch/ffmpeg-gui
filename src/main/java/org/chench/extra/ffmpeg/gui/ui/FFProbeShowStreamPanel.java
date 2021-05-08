package org.chench.extra.ffmpeg.gui.ui;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.chench.extra.ffmpeg.gui.ffmpeg.CmdOutput;
import org.chench.extra.ffmpeg.gui.ffmpeg.FFProbeHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * ffprobe查看流信息面板
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.ui.FFProbeShowStreamPanel
 * @date: 4/30/21
 */
@Slf4j
public class FFProbeShowStreamPanel extends ContainerBase {
    private JTextField pathField;
    private JTextArea outputArea;
    private JTextArea commandArea;
    private JPopupMenu pm;
    private JMenuItem copy;
    private JMenuItem save;
    private Clipboard cboard;
    private String lastOpenFileDir;
    private String lastSaveFileDir;

    @Override
    public JComponent init() {
        this.pathField = new PlaceholderTextField(23);
        TransferHandler transferHandler = new PathFieldTransferHandler(this.pathField);
        this.pathField.setTransferHandler(transferHandler);
        this.pathField.setToolTipText(getMessage("tooltip.file.path"));
        //this.pathField.setPlaceholder(getMessage("tooltip.file.path"));

        this.outputArea = new JTextArea(20, 10);
        this.outputArea.setTransferHandler(transferHandler);
        this.outputArea.addMouseListener(new OutputAreaMouseListener());

        // 右键弹出菜单
        this.pm=new JPopupMenu();
        // 粘贴板
        this.cboard = getToolkit().getSystemClipboard();

        //复制
        copy=new JMenuItem(getMessage("menu.item.copy"));
        copy.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        //保存
        save=new JMenuItem(getMessage("menu.item.save"));
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });
        pm.add(copy);
        pm.add(save);
        this.outputArea.add(pm);

        this.commandArea = new JTextArea(2, 10);
        this.commandArea.setEditable(false);
        this.commandArea.setBackground(Color.BLACK);
        this.commandArea.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(initNorthPanel(), BorderLayout.NORTH);
        panel.add(initCenterPanel(), BorderLayout.CENTER);
        //panel.add(initSouthPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JComponent initNorthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //panel.setBorder(new LineBorder(Color.BLACK));
        panel.add(this.pathField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton selectFileBtn = new JButton(getMessage("btn.select.file"));
        //selectFileBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
        selectFileBtn.addActionListener(e -> {
            JFileChooser fileChooser = StringUtils.isAllBlank(this.lastOpenFileDir) ? new JFileChooser() : new JFileChooser(this.lastOpenFileDir);
            int option = fileChooser.showOpenDialog(outputArea);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                this.lastOpenFileDir = file.getParent();
                System.out.println("selected file: " + file);
                doCmd(file.getPath());
            }
        });
        buttonPanel.add(selectFileBtn);

        JButton saveBtn = new JButton(getMessage("btn.save"));
        saveBtn.addActionListener(e -> doSave());
        buttonPanel.add(saveBtn);
        panel.add(buttonPanel, BorderLayout.EAST);
        return panel;
    }

    private JComponent initCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //panel.setBorder(new LineBorder(Color.BLACK));

        JScrollPane outputPane=new JScrollPane(this.outputArea);
        outputPane.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
        JScrollPane cmdPane = new JScrollPane(this.commandArea);
        cmdPane.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, outputPane, cmdPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(1.0);
        //splitPane.setDividerLocation(600);
        Dimension minimumSize = new Dimension(50, 10);
        //listScrollPane.setMinimumSize(minimumSize);
        cmdPane.setMaximumSize(minimumSize);
        panel.add(splitPane);
        return panel;
    }

    private JComponent initSouthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //panel.setBorder(new LineBorder(Color.BLACK));
        panel.setBorder(new TitledBorder(getMessage("title.border.command")));
        panel.add(this.commandArea);
        return panel;
    }

    /**
     * 右键菜单事情处理
     * @param e
     */
    private void action(ActionEvent e) {
        String str = e.getActionCommand();
        if(str.equals(copy.getText())) {
            // 复制
            this.outputArea.grabFocus();
            this.outputArea.selectAll();
            String selection = outputArea.getSelectedText();
            if (selection == null) {
                return;
            }
            StringSelection clipString =new StringSelection(selection);
            cboard.setContents(clipString, clipString);
            //JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "复制成功！");
        } else if(str.equals(save.getText())) {
            // 保存
            doSave();
        }
    }

    /**
     * 保存命令行输出内容
     */
    private void doSave() {
        String text = outputArea.getText();
        if (text.length() <= 0) {
            return;
        }
        JFileChooser fileChooser = StringUtils.isAllBlank(this.lastSaveFileDir) ? new JFileChooser() : new JFileChooser(this.lastSaveFileDir);
        int option = fileChooser.showSaveDialog(this.outputArea);
        if(option == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            this.lastSaveFileDir = file.getParent();
            System.out.println("selected file: " + file);
            //label.setText("File Saved as: " + file.getName());
            FileUtil.writeString(text, file, "UTF-8");
        }else{
            //label.setText("Save command canceled");
            System.out.println("取消保存");
        }
    }

    /**
     * 执行命令
     * @param filePath
     * @throws IOException
     */
    private void doCmd(String filePath) {
        try {
            // String cmd = String.format("ffprobe -show_streams -of json %s", filePath);
            CmdOutput output = FFProbeHelper.exec(filePath, Arrays.asList(
                    ImmutablePair.of("show_streams", ""),
                    ImmutablePair.of("of", "json")));
            if (StringUtils.isNotBlank(output.getError()) && StringUtils.isAllBlank(output.getSuccess())) {
                outputArea.setForeground(Color.RED);
                outputArea.setText(output.getError());
            } else {
                outputArea.setForeground(Color.BLACK);
                outputArea.setText(output.getSuccess());
            }
            outputArea.setCaretPosition(0);
            commandArea.setText(output.getCommand());
        } catch (IOException e) {
            log.error("do cmd ex, stack: {}", e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     * 鼠标拖动文件处理
     */
    class PathFieldTransferHandler extends TransferHandler {
        private JTextField field;
        public PathFieldTransferHandler(JTextField field) {
            this.field = field;
        }

        @Override
        public boolean importData(JComponent comp, Transferable t) {
            try {
                Object o = t.getTransferData(DataFlavor.javaFileListFlavor);
                String filepath = o.toString();
                if (filepath.startsWith("[")) {
                    filepath = filepath.substring(1);
                }
                if (filepath.endsWith("]")) {
                    filepath = filepath.substring(0, filepath.length() - 1);
                }
                //System.out.println(filepath);
                field.setText(filepath);
                if (new File(filepath).isDirectory()) {
                    // 拖动的是文件夹时不做处理
                    return true;
                }

                // 执行命令
                doCmd(filepath);
                return true;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean canImport(JComponent comp, DataFlavor[] flavors) {
            for (int i = 0; i < flavors.length; i++) {
                if (DataFlavor.javaFileListFlavor.equals(flavors[i])) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 鼠标右键点击事情
     */
    class OutputAreaMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON3) {
                //selectAll.setEnabled(isCanCopy());
                copy.setEnabled(true);
                save.setEnabled(true);
                pm.show(outputArea, e.getX(), e.getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
