package com.rabbitq.utils;


import com.rabbitq.entity.TargetOptionsEntity;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadScan implements Runnable {


    /**
     * 添加动作监听器的线程类
     */

    @Override
    public void run() {
        System.out.println( this.getClass().getSimpleName()+": 主线程开始运行");
    }

    private String hostName = "";       // 域名
    //, ,
    public void scanAction(TargetOptionsEntity targetOptionsEntity) {
        String scanIP=targetOptionsEntity.getHost();
        String argPorts=targetOptionsEntity.getPorts();
        int numOfThreads=targetOptionsEntity.getNumOfThreads();
        int timeOut=targetOptionsEntity.getTimeOut();
        List<Integer> allPorts = new ArrayList<Integer>();
        //设置目标地址
        TCPThread.scanIP = scanIP;
        TCPThread.timeOut=timeOut;
        try {
            allPorts = Utils.calculatePort(argPorts);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("最大启动线程数为"+ numOfThreads);
        int portSize = allPorts.size();
        if(portSize<numOfThreads){
            numOfThreads=portSize;
        }
        List<List<Integer>> scanPorts= Utils.averageAssign(allPorts,numOfThreads);
        ThreadPoolExecutor myExecutor = new ThreadPoolExecutor(numOfThreads, numOfThreads, 200, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
        TCPThread.numOfThreads = numOfThreads;
        for (int i = 0; i < numOfThreads; i++){
            ArrayList<Integer> castedList = new ArrayList<>(scanPorts.get(i));
            Integer[] array = (Integer[]) castedList.toArray(new Integer[castedList.size()]);
            myExecutor.submit(new TCPThread("T" + i, array));

        }

        myExecutor.shutdown();



    }


}
