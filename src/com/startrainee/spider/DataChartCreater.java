package com.startrainee.spider;

import com.startrainee.spider.data.News;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataChartCreater {

    public static void createNewsChart(String filePath){
        List<News> news = readNewsFile(filePath);
        HashMap<LocalDate,Integer> dateRange = getNewsHashMap(news);
        createChart(dateRange);

    }

    private static void createChart(HashMap<LocalDate, Integer> dateRange) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        Set<LocalDate> keySet = dateRange.keySet();
        List<LocalDate> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);

        for(LocalDate str : keyList){
            dataSet.addValue(dateRange.get(str), str, str);
            System.out.println(str + "$" + dateRange.get(str));
        }

        JFreeChart barChart = ChartFactory.createLineChart(
                "Neusoft News Counts",
                "Datas",
                "Numbers",
                dataSet,
                PlotOrientation.VERTICAL,
                true, true, false);

        int width = 640; /* Width of the image */
        int height = 480; /* Height of the image */
        File barChart3D = new File("barChart3D1.jpeg");
        try {
            ChartUtilities.saveChartAsJPEG(barChart3D, barChart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<LocalDate,Integer> getNewsHashMap(List<News> news) {
        HashMap<LocalDate,Integer> dateRange = new HashMap<>();
        for(News newsTemp : news){
            LocalDate date = changeToLocalDate(newsTemp.getDate());
            Integer newInt = 0;
            if(dateRange.keySet().contains(date)){
                newInt = dateRange.get(date) + 1;
            }else{
                newInt++;
            }
            dateRange.put(date,newInt);
        }
        return dateRange;
    }

    private static LocalDate changeToLocalDate(String date) {
        String[] datasTemp = date.split("\\.");
        return LocalDate.of(Integer.valueOf(datasTemp[2]),Integer.valueOf(datasTemp[0]),Integer.valueOf(datasTemp[1]));
    }

    private static List<News> readNewsFile(String filePath) {
        List<News> news = new ArrayList<>();
        File file = new File(filePath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.ready()){
                String newsStr  = bufferedReader.readLine();

                String[] newsStrArray = newsStr.split("[|]+");
                News newsTemp = new News(newsStrArray[1],newsStrArray[2],newsStrArray[3],newsStrArray[4]);
                news.add(newsTemp);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return news;



    }


    public static void main(String[] args) throws IOException {
        createNewsChart("spider_data_1.txt");


  /*  DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //dataset.addValue(200, fait, speed);
        //dataset.addValue(10, audi, speed);
        //dataset.addValue(40, ford, speed);

    JFreeChart barChart = ChartFactory.createBarChart3D(
            "Neusoft News Counts",
            "Datas",
            "Numbers",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false);

    int width = 640; *//* Width of the image *//*
    int height = 480; *//* Height of the image *//*
    File barChart3D = new File("barChart3D.jpeg");
        ChartUtilities.saveChartAsJPEG(barChart3D, barChart, width, height);*/
    }
}
