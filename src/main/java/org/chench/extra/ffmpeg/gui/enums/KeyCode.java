package org.chench.extra.ffmpeg.gui.enums;

/**
 * 键盘按键
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.enums.KeyCode
 * @date: 5/10/21
 */
public enum KeyCode {
    CODE_C(67),
    CODE_V(86);

    private int code;
    KeyCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
