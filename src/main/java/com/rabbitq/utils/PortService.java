package com.rabbitq.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public enum PortService {

    INSTANCE;

    private Map<Integer, String> portServiceEntity = new HashMap<>();

    private PortService() {
        File file = new File(System.getProperty("user.dir") + "\\PortService");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] a = line.split("\t");
                try {
                    portServiceEntity.put(Integer.parseInt(a[0]), a[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("\033[31m如需判断开放端口默认对应服务需复制PortService文件到当前Jar包所在目录下\033[0m");
        }


    }

    public Map<Integer, String> getService() {
        return portServiceEntity;
    }
}
