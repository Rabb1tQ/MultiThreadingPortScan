package com.rabbitq.utils;

import com.rabbitq.entity.TargetOptionsEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class Utils {
    public static <T> List<List<T>> averageAssign(List<T> source, int n){
        List<List<T>> result=new ArrayList<List<T>>();
        int remaider=source.size()%n;  //(先计算出余数)
        int number=source.size()/n;  //然后是商
        int offset=0;//偏移量
        for(int i=0;i<n;i++){
            List<T> value=null;
            if(remaider>0){
                value=source.subList(i*number+offset, (i+1)*number+offset+1);
                remaider--;
                offset++;
            }else{
                value=source.subList(i*number+offset, (i+1)*number+offset);
            }
            result.add(value);
        }
        return result;
    }
    public static List<Integer> calculatePort(String ports) throws Exception {
        List<String> listResult = new ArrayList<>();
        List<String> templistPort = Arrays.asList(ports.split(","));
        List<Integer> portList = new ArrayList<Integer>();
        if (templistPort.size() > 0) {
            for (int i = 0; i < templistPort.size(); i++) {
                String strPort = templistPort.get(i);
                if (strPort.contains("-")) {
                    List<String> temp01 = Arrays.asList(strPort.split("-"));
                    int min = Integer.parseInt(temp01.get(0));
                    int max = Integer.parseInt(temp01.get(1));
                    for (int j = min; j <= max; j++) {
                        String strTmpPort = String.valueOf(j);
                        if (!portList.contains(strTmpPort)) {
                            portList.add(Integer.valueOf(strTmpPort));
                            // portList.contains(strTmpPort);
                        }
                    }
                } else if (!portList.contains(strPort)) {
                    portList.add(Integer.valueOf(strPort));
                }
            }
        } else {
            throw new Exception("端口参数错误，检查后重新输入");
        }
        return portList;
    }

    // 检查端口的范围
    public static void checkPortRange(int port) throws InputMismatchException {
        if (port < 0 || port > 65535)
            throw new InputMismatchException();
    }

}
