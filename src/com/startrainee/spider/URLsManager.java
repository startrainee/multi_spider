package com.startrainee.spider;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

public class URLsManager {

    private final static  int EXPAND_SIZE = 1000;
    private LinkedBlockingQueue<String> urls = new LinkedBlockingQueue<>(1000);
    private Vector<String> hadGetRULs = new Vector<>();


    public Vector<String> getHadGetRULs() {
        return hadGetRULs;
    }


    public synchronized String getNewUrl(){
        String newURL = urls.poll();
        while(hadGetRULs.contains(newURL)){
            if(!urls.isEmpty()){
                return null;
            }
            newURL = urls.poll();
        }
        if(newURL != null){
            addHadGetVector(newURL);
        }
        return newURL;
    }

    public synchronized boolean addURL(String url){
        if(hadGetRULs.contains(url)){
            return false;
        }
        if(urls.remainingCapacity() <= 0){
            LinkedBlockingQueue<String> newUrls = new LinkedBlockingQueue<>(urls.size() + EXPAND_SIZE);
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
    public synchronized void addHadGetVector(String url){
        if(!hadGetRULs.contains(url)){
            hadGetRULs.add(url);
        }
    }
}
