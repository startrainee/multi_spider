import com.startrainee.spider.HTMLParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        LinkedBlockingQueue<String> urls = new LinkedBlockingQueue<>(5);
        LinkedBlockingQueue<String> urls2 = new LinkedBlockingQueue<>(10);
        urls.add("1");
        urls.add("2");
        urls.add("3");
        urls.add("4");
        urls.add("5");

        urls2.addAll(urls);
        urls2.forEach(System.out::println);
        System.out.println(urls.remainingCapacity());
        System.out.println(urls2.remainingCapacity());

        //URL url = new URL("http://www.baidu.com");
        URL url = new URL("http://www.neusoft.com/cn/news/index.jsp?type=39");
        StringBuilder stringBuilder = new StringBuilder();
        URLConnection urlcon = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        while (br.ready()){
            stringBuilder.append(br.readLine().trim()).append("\n");
        }
        HTMLParser htmlParser = new HTMLParser();
        String newdata = htmlParser.getAreaString(stringBuilder.toString(),"<div class=\"list_page_r\"></div>","<div class=\"list_page\">");
        System.out.println(newdata);
        //String regex = "\"^?CurrentPage=[0-9]+[A-Za-z0-9=&;]*\"$\"";
        //String regex = "html[/0-9]+.html";
        String regex = "\\d+.\\d+.\\d+";
        String source = "<b class=\"blue2\">09.21.2017</b>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            System.out.println("match.group(): "+matcher.group());
            System.out.println();
        }


    }
}
