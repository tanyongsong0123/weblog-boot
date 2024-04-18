package com.tys.weblog.common.utils;

import java.io.File;

public class Fileutil {


    public static void renameFile(String fileName){
        File f = new File(fileName);
        print(f);
    }

    public static void print(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                String filename = f.getName();
                String path = f.getParent();
                String newFileName = filename.replace(" - 犬小哈专栏_", "").replace(" - 犬小哈专栏","");
                f.renameTo(new File(path + "\\" + newFileName));
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (File file : fileArray) {
                        //递归调用
                        print(file);
                    }
                }
            } else {
                String filename = f.getName();
                String path = f.getParent();
                String newFileName = filename.replace(" - 犬小哈专栏_", "").replace(" - 犬小哈专栏","");
                f.renameTo(new File(path + "\\" + newFileName));
            }
        }
    }

    public static void main(String[] args) {
        Fileutil.renameFile("D:\\CODE\\weblog-qt\\html");
    }
}
