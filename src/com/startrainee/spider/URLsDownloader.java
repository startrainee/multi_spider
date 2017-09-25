package com.startrainee.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLsDownloader {

    public String downLoaderURL(String url){

        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL mURL = new URL(url);
            URLConnection urlCon = mURL.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            while (br.ready()){
                stringBuilder.append(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
