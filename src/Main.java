import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.LinkedBlockingQueue;

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

        URL url = new URL("http://www.baidu.com");
        URLConnection urlcon = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        while (br.ready()){
            System.out.println(br.readLine());
        }

    }
}
