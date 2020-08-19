package com.rabbin.learn.hotloader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

public class ClassListener implements Runnable {
    private Map<String,FileDefine> fileDefineMap;

    public ClassListener(Map<String, FileDefine> fileDefineMap) {
        this.fileDefineMap = fileDefineMap;
    }

    @Override
    public void run() {
        File file = new File(FileDefine.WATCH_PACKAGE);
        File[] files = file.listFiles();
        for (File watchFile : files){
            long newTime = watchFile.lastModified();
            FileDefine fileDefine = fileDefineMap.get(watchFile.getName());
            long oldTime = fileDefine.getLastDefine();
            //如果文件被修改了,那么重新生成累加载器加载新文件
            if (newTime!=oldTime){
                fileDefine.setLastDefine(newTime);
                loadMyClass();
            }
        }
    }

    private void loadMyClass(){
        try {
            CustomClassLoader customClassLoader = new CustomClassLoader();
            Class<?> cls = customClassLoader.loadClass("com.rabbin.learn.hotloader.service.Hello",false);
            Object test = cls.newInstance();
            Method method = cls.getMethod("sayHello");
            method.invoke(test);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
