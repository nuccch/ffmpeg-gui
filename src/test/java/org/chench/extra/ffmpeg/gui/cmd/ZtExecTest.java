package org.chench.extra.ffmpeg.gui.cmd;

import org.zeroturnaround.exec.ProcessExecutor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: chench9
 * @desc: org.chench.extra.ZtExecTest
 * @date: 4/28/21
 */
public class ZtExecTest {
    public static void main(String[] args) throws InterruptedException, TimeoutException, IOException {
        String cmd = "/Users/chench/opt/ffmpeg-2021-02/ffprobe -show_streams -of json output.mp4";
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errStream = new ByteArrayOutputStream();
        String result = new ProcessExecutor().command(cmd)
//                .redirectOutput(outStream)
//                .redirectError(errStream)
                .readOutput(true)
                .destroyOnExit()
                .directory(new File("/Users/chench/Downloads/"))
                .execute()
                .outputUTF8();
        String out = outStream.toString("UTF-8");
        String err = errStream.toString("UTF-8");
        System.out.println("------------------------------");
        System.out.println(out);
        System.out.println("------------------------------");
        System.out.println(err);
        System.out.println("==============================");
        System.out.println(result);
    }
}
