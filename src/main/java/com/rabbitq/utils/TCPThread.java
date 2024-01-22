package com.rabbitq.utils;

import java.net.*;
import java.util.Map;

public class TCPThread extends Thread {

    //目标IP
    static String scanIP = "";

    //目标端口
    private Integer[] ports;

    // 最大启动线程数
    static int numOfThreads;

    static int timeOut;

    // 端口和对应服务的Map
    private static Map<Integer, String> portForService = PortService.INSTANCE.getService();



    public TCPThread(String name, Integer[] ports) {
        super(name);
        this.ports = ports;

    }

    /**
     *  运行方法
     */
    @Override
    public void run() {
        try {
            portScan(InetAddress.getByName(scanIP));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 端口扫描，按照分好的端口列表进行扫描
     */
    private void portScan(InetAddress host) throws UnknownHostException {
        Socket theTCPSocket;
        int portSize = this.ports.length;
        Socket tempSocket;
        String ip = host.getHostAddress();
        InetAddress address = InetAddress.getByName(ip);
        SocketAddress socketAddress;
        for (int i = 0; i < portSize; i++) {
            try {
                int tempPort = ports[i];
                tempSocket = new Socket();
                socketAddress = new InetSocketAddress(address, tempPort);
                tempSocket.connect(socketAddress, timeOut); // 超时时间
                tempSocket.close();
                StringBuilder out = new StringBuilder();
                out.append(ip).append(":")
                        .append(tempPort).append("\t            ")
                        .append(portForService.getOrDefault(tempPort, "(UNKNOWN_SERVICE)"));
                System.out.println(out.toString());

            } catch (Exception e) {
                continue;
            }
        }
    }

    @Override
    public String toString() {
        return "ThreadName:" + this.getName();
    }
}