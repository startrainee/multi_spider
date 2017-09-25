package com.startrainee.spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

public class URLsManager {

    private final static  int EXPAND_SIZE = 500;
    private LinkedBlockingQueue<String> urls = new LinkedBlockingQueue<>(500);
    private Vector<String> hadGetRULs = new Vector<>();


    public synchronized String getNewUrl(){
        String newURL = urls.poll();
        while(hadGetRULs.contains(newURL) && !urls.isEmpty()){
            newURL = urls.poll();
        }
        return newURL;
    }

    public synchronized boolean addURL(String url){
        if(hadGetRULs.contains(url)){
            return false;
        }
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

    public boolean isEmpty(){
        return urls.isEmpty();
    }
    public void addHadGetVector(String url){
        if(!hadGetRULs.contains(url)){
            hadGetRULs.add(url);
        }
    }
}
