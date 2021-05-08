package org.chench.extra.ffmpeg.gui.ffmpeg;

import lombok.Data;

/**
 * 命令输出对象封装
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.ffmpeg.CmdOutput
 * @date: 4/30/21
 */
@Data
public class CmdOutput {
    /** 执行成功消息 */
    private String success;
    /** 执行错误消息 */
    private String error;
    /** 执行的命令 */
    private String command;
    /** 执行出错时的异常对象 */
    private Exception exception;

    public CmdOutput() { }

    public CmdOutput(String command) {
        this.command = command;
    }
}
