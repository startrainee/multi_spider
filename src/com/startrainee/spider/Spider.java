package com.startrainee.spider;

import com.sun.webkit.network.URLs;

import java.util.List;

public class Spider implements Runnable {

    private HTMLParser htmlParser = new HTMLParser();
    private URLsDownloader urLsDownloader = new URLsDownloader();
    private URLsManager urLsManager = new URLsManager();
    private HTMLOutputer htmlOutputer = new HTMLOutputer();
    private String rootURL;

    public Spider(String rootURL) {
        htmlParser = new HTMLParser();
        urLsDownloader = new URLsDownloader();
        urLsManager = new URLsManager();
        htmlOutputer = new HTMLOutputer();
        this.rootURL = rootURL;
        urLsManager.addURL(rootURL);
    }

    public void start() {


        int threadCount = 0;
        while (threadCount < 5) {
            threadCount++;
            new Thread(this).start();
        }
        System.out.println("5 threads have started.");

    }


    @Override
    public void run() {

        while(!urLsManager.isEmpty()){
            String URL = urLsManager.getNewUrl();
            String htmlData = urLsDownloader.downLoaderURL(rootURL);
            System.out.println(htmlData);
            List<String> newUrls = htmlParser.getNewURLs(htmlData);
            urLsManager.addURLs(newUrls);
            List<String> parsedData = htmlParser.getDatas(htmlData);
            htmlOutputer.outPut(parsedData);
        }
    }

    public static void main(String[] args) {
        String rootUrl = "http://www.neusoft.com/cn/news/index.jsp?type=39";
        new Spider(rootUrl).start();
    }
}
