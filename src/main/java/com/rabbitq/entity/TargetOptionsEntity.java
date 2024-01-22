package com.rabbitq.entity;

import com.beust.jcommander.Parameter;

public class TargetOptionsEntity {

    @Parameter(names = {"ports","-p"},
            description = "目标端口，示例：1,2-30,80",
            required = true)
    private String ports;

    @Parameter(names = {"host","-h"},
            description = "目标IP",
            required = true)
    private String host;
    @Parameter(names = {"numOfThreads","-t"},
            description = "线程数")
    private int numOfThreads=100;

    @Parameter(names = {"timeOut","-time"},
            description = "超时时间")
    private int timeOut=200;
    @Parameter(names = {"help", "--help"},
            description = "查看帮助信息",
            help = true)
    private boolean help;

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    public String getPorts() {
        return ports;
    }

    public String getHost() {
        return host;
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
}
