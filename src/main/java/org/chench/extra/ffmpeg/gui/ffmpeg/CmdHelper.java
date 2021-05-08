package org.chench.extra.ffmpeg.gui.ffmpeg;

import org.apache.commons.exec.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 命令工具类
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.ffmpeg.CmdHelper
 * @date: 5/7/21
 */
public abstract class CmdHelper {
    /**
     * 执行命令
     * @param cmd 命令
     * @return
     * @throws IOException
     */
    public static CmdOutput exec(String cmd) throws IOException {
        CmdOutput output = new CmdOutput(cmd);
        CommandLine cmdLine = CommandLine.parse(cmd);
        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        boolean ex = false;
        try {
            executor.setStreamHandler(new PumpStreamHandler(out, err));
            executor.setProcessDestroyer(new ShutdownHookProcessDestroyer());
            executor.execute(cmdLine);
        } catch (ExecuteException e) {
            ex = true;
            output.setException(e);
            // e.printStackTrace();
        } finally {
            String outResult = out.toString("UTF-8");
            String errResult = err.toString("UTF-8");
            if (!ex) {
                output.setSuccess(outResult);
                output.setError(errResult);
                System.out.println(outResult);
            } else {
                System.out.println(cmd);
                System.out.println(errResult);
                output.setError(errResult);
            }
        }
        return output;
    }
}
