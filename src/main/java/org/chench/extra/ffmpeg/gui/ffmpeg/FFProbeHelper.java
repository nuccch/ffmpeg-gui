package org.chench.extra.ffmpeg.gui.ffmpeg;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.List;

/**
 * ffprobe命令工具类
 * @author: chench9
 * @desc: org.chench.extra.ffmpeg.gui.ffmpeg.FFProbeHelper
 * @date: 5/7/21
 */
@Slf4j
public class FFProbeHelper extends CmdHelper {
    /**
     * 执行ffprobe命令
     * @param input
     * @param args
     * @return
     * @throws IOException
     */
    public static CmdOutput exec(String input, List<ImmutablePair<String, String>> args) throws IOException {
        StringBuilder builder = new StringBuilder().append("ffprobe ");
        args.stream().forEach(arg -> {
            builder.append("-").append(arg.getLeft()).append(" ").append(arg.getRight());
        });
        builder.append(" ").append(input);
        String cmd = builder.toString();
        log.info(cmd);
        return exec(cmd);
    }

    private FFProbeHelper() { }
}
