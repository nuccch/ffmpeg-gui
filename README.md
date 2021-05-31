# README

## What

这是一个ffmpeg命令行工具的GUI封装项目，对开发者友好的音视频处理工具。

## Why

主要出于2点考虑：

第一：出于工作需要，要经常通过ffmpeg的相关命令查看音视频资源的信息，但是每次都需要打开命令行，而且对于文件路径的处理，是在太麻烦。
因此希望有一个带界面的工具，直接鼠标拖动文件就可以查看到文件的信息，减少每次都要执行命令。对于需要经常查看音视频文件的信息的场景，实在太需要一个简单的工具了。

第二：对于一些常见的音视频处理需求，最好有一个简单的，免安装的，跨平台的工具就可以实现，比如：视频帧截取，音视频资源提取，音视频资源合成等等。
不需要动不动就去下载那些专业性强，不一定跨平台，需要安装的大型软件。

## How

本人对Java相对熟悉，而且也满足跨平台的要求，所以基于Java Swing开发GUI界面，底层调用ffmpeg命令行工具实现。
软件打包为一个压缩包进行发布，免安装，解压就可以使用。

## Remark

ffprobe命令部分，为需要经常使用ffprobe命令工具的开发人员准备。
ffppeg命令部分，为音视频处理小白开发。

## 已经存在FFmpeg GUI项目  

https://github.com/mifi/lossless-cut   lossless-cut  
https://gitee.com/haujet/QuickCut    Quick Cut  
https://github.com/MattMcManis/Axiom   Axiom  
https://github.com/WinFF/winff  winff 

## Dependency

1.Apache Commons Exec ：https://commons.apache.org/proper/commons-exec/  
2.Apache Commons Lang ：https://commons.apache.org/proper/commons-lang/  
3.beautyeye           ：https://gitee.com/jackjiang/beautyeye  
4.Hutool              ：https://www.hutool.cn/  
5.spring-framework    ：https://spring.io/projects/spring-framework  
