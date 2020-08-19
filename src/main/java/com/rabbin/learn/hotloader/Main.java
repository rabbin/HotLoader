package com.rabbin.learn.hotloader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static Map<String,FileDefine> fileDefineMap = new HashMap<>();

    public static void main(String[] args){
        File file = new File(FileDefine.WATCH_PACKAGE);
        File[] files = file.listFiles();
        for (File watchFile : files){
            long lastModified = watchFile.lastModified();
            String name = watchFile.getName();
            FileDefine fileDefine = new FileDefine(name,lastModified);
            fileDefineMap.put(name,fileDefine);
        }
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new ClassListener(fileDefineMap), 0,500, TimeUnit.MICROSECONDS);
    }

}
