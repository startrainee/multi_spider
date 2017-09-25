package com.startrainee.spider;

import java.io.File;
import java.util.List;

public class HTMLOutputer {
    private static int fileCount = 0;
    private static String baseFileName = "spider_data_";
    public void outPut(List<String> parseredData) {
        String fileName = baseFileName + fileCount +".txt";
        File file = new File(fileName);
        while (file.exists()){
            fileCount++;
            fileName = baseFileName + fileCount;
            file = new File(fileName);
        }
        file.mkdirs();
    }
}
