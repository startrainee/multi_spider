package com.startrainee.spider;

import com.sun.webkit.network.URLs;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Spider implements Runnable {

    private HTMLParser htmlParser = new HTMLParser();
    private URLsDownloader urLsDownloader = new URLsDownloader();
    private URLsManager urLsManager = new URLsManager();
    private HTMLOutputer htmlOutputer = new HTMLOutputer();
    private Vector<String> bufferOfdata = new Vector<>();
    private String rootURL;
    private static int threadCount = 0;

    public Spider(String rootURL) {
        htmlParser = new HTMLParser();
        urLsDownloader = new URLsDownloader();
        urLsManager = new URLsManager();
        htmlOutputer = new HTMLOutputer();
        this.rootURL = rootURL;
        urLsManager.addURL(rootURL);
    }

    public void start() {

        threadCount = 0;
        while (threadCount < 5) {
            if(threadCount!=1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            threadCount++;
            new Thread(this).start();
        }
        System.out.println("5 threads have started.");

    }


    @Override
    public void run() {

        while(!urLsManager.isEmpty()){
            System.out.println("currentThread : " + Thread.currentThread().getName());
            String URL = urLsManager.getNewUrl();
            if(URL == null){
                System.out.println("urLsManager.getNewUrl()");
                continue;
            }
            String htmlData = urLsDownloader.downLoaderURL(URL);
            List<String> newUrls = htmlParser.getNewURLs(htmlData);
            urLsManager.addURLs(newUrls);
            List<String> parsedData = htmlParser.getDatas(htmlData);
            System.out.println(parsedData.size());
            bufferOfdata.addAll(parsedData);
        }
        threadCount--;
        if(threadCount <= 0){
            htmlOutputer.outPut(bufferOfdata);
            DataChartCreater.createNewsChart("spider_data_1.txt");
        }
        //urLsManager.getHadGetRULs().forEach(System.out::println);
    }

    public static void main(String[] args) {
       String rootUrl = "http://www.neusoft.com/cn/news/index.jsp?CurrentPage=0&type=39&keywords=&srchfield=0&startdate=&enddate=";
        new Spider(rootUrl).start();

    }
}
