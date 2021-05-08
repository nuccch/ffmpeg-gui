package org.chench.extra.ffmpeg.gui.cmd;

import org.apache.commons.exec.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ApacheExecTest
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        String cmd = "ffprobe -show_streams -of json /Users/chench/Downloads/output_av1.mp4";
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
            // e.printStackTrace();
        } finally {
            String outResult = out.toString("UTF-8");
            String errResult = err.toString("UTF-8");
            if (!ex) {
                System.out.println(outResult);
            } else {
                System.out.println(cmd);
                System.out.println(errResult);
            }
        }
    }
}
