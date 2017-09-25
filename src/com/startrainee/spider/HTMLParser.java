package com.startrainee.spider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {

    public List<String> getNewURLs(String HTMLdata) {
        List<String> newURLs = new ArrayList<>();

        String areaOfURLs = getAreaString(HTMLdata, "<div class=\"manu\">", "</div>");
        String rootPath = "http://www.neusoft.com/cn/news/index.jsp?";

        String regex = "CurrentPage=[0-9]+[A-Za-z0-9=&;]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(areaOfURLs);
        while (matcher.find()) {
            String newURL = rootPath + matcher.group();
            newURLs.add(newURL);
        }
        return newURLs;
    }

    public List<String> getDatas(String HTMLdata) {
        System.out.println("start");
        List<String> datas = new ArrayList<>();
        //String areaOfDatas = getAreaString(HTMLdata, "<div class=\"new_list\">", "<div class=\"list_page\">");
        String regex = Pattern.quote("<div class=\"new_list_tit\">") + "(.*?)" + Pattern.quote("<div class=\"new_list_more\">");
        //+ "(.*?)" + Pattern.quote("</div>");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(HTMLdata);
        while (matcher.find()) {
            String dataTemp = matcher.group();
            System.out.println(dataTemp);
            dataTemp = dataTemp.trim()
                    .replaceAll("<a\\shref=\"","|")
                    .replaceAll("\">\\s<b>","|")
                    .replaceAll("<[^>]*>","|")
                    .replaceAll("\\s([|])+","|")
                    .replaceAll("[|]+","|")
                    .replaceAll("([|])+","|")
                    .replaceAll("&([a-z]?)+;","");
            datas.add(dataTemp);
        }


        return datas;
    }

    public String getAreaString(String HTMLdata, String startStr, String endStr) {

        //String areaStringOfsubStart = HTMLdata.substring(HTMLdata.indexOf(startStr));
        //String regex = startStr +".+"+endStr;
        String regex = Pattern.quote(startStr) + "(.*?)" + Pattern.quote(endStr);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(HTMLdata);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
        //return areaStringOfsubStart.substring(0, areaStringOfsubStart.indexOf(endStr));
    }

/*    public static void main(String[] args) {
        List<String> list = new HTMLParser().getNewURLs(new URLsDownloader().downLoaderURL("http://www.neusoft.com/cn/news/index.jsp?type=39"));
        //list.forEach(s-> System.out.println(Arrays.toString(s.trim().split("[|]+"))));
        list.forEach(System.out::println);
    }*/


}
