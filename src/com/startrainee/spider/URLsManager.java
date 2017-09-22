package com.startrainee.spider;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class URLsManager {

    private final static  int EXPAND_SIZE = 500;
    private LinkedBlockingQueue<String> urls = new LinkedBlockingQueue<>(500);


    public synchronized String getNewUrl(){
        return urls.poll();
    }

    private synchronized boolean addURL(String url){

        if(urls.remainingCapacity() <= 0){
            LinkedBlockingQueue<String> newUrls = new LinkedBlockingQueue<>(urls.size()+ EXPAND_SIZE);
            newUrls.addAll(urls);
            urls = newUrls;
        }

        return urls.add(url);
    }

    public synchronized void addURLs(List<String> urlList){
        urlList.forEach(this::addURL);
    }
}
