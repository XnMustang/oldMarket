package com.wlrss.oldmarket.utils;

import com.wlrss.oldmarket.entity.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * 爬虫
 */

@Component  //交给spring  直接Autowrid注入
public class ParseUtils {

    public ArrayList<Content> parseJD(String keyWords) throws IOException {
        //获取请求  需要联网  ajax不能获取到，因为模拟客户端请求（客户端不能异步）
        String url = "https://search.jd.com/Search?keyword=" + keyWords;

        //解析网页  Jsoup返回Document就是浏览器Document对象
        Document document = Jsoup.parse(new URL(url), 30000);   //30s爬取数据

        //在js中能用到的方法，在这里都能用
        Element element = document.getElementById("J_goodsList");

        //打印出所有dom元素
//        System.out.println(element.html());
        //获取所有的li标签
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> goodsList = new ArrayList<>();

        //获取元素中的内容  这里的el就是li标签
        for (Element el : elements) {
            //图片特别多的网站，一般都是图片延迟加载，需要检查页面图面哪个标签是指向img的path
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);

            goodsList.add(content);
        }
        return goodsList;
    }

    //测试一把
    public static void main(String[] args) throws IOException {
        ParseUtils parseUtils = new ParseUtils();
        ArrayList<Content> contents = parseUtils.parseJD("日用百货");
        for (Content content : contents) {
            System.out.println(content);
        }
    }
}
