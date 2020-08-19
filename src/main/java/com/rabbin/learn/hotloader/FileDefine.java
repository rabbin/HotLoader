package com.rabbin.learn.hotloader;

public class FileDefine {
    public static String WATCH_PACKAGE = "D:\\projects\\java\\JavaLearn\\HotLoader\\target\\classes\\com\\rabbin\\learn\\hotloader\\service";

    private String fileName;

    private Long lastDefine;

    public FileDefine(String fileName, long lastDefine) {
        this.fileName = fileName;
        this.lastDefine = lastDefine;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getLastDefine() {
        return lastDefine;
    }

    public void setLastDefine(Long lastDefine) {
        this.lastDefine = lastDefine;
    }
}
