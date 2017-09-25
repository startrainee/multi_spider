package com.startrainee.spider;

import java.io.*;
import java.util.List;

public class HTMLOutputer {
    private static int fileCount = 0;

    public void outPut(List<String> parseredData) {
            File file = createFile();
            writeIntoFile(file, parseredData);
    }

    private File createFile() {

        String baseFileName = "spider_data_";
        String fileName = baseFileName + fileCount +".txt";
        File file = new File(fileName);
        while (!file.exists()){
            fileCount++;
            fileName = baseFileName + fileCount + ".txt";
            file = new File(fileName);
            try {
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write("");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("$ file : " + fileName + " is exists.");
        return file;
    }

    private void writeIntoFile(File file, List<String> parseredData) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true));
                parseredData.forEach(s -> {
                    try {
                        bufferedWriter.write(s);
                        bufferedWriter.write("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
