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
import java.util.Date;
import java.util.Random;

/**
 * 爬虫
 */

@Component  //交给spring  直接Autowrid注入
public class ParseUtils {

    public static ArrayList<Content> parseJD(String keyWords) throws IOException {
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
            String goodsimg = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String described = el.getElementsByClass("p-name").eq(0).text();

            String goodsName = described.substring(0,6);   //截取描述信息变为商品名称
            String describe = described.substring(0,25);   //前100个字为商品描述
            String goodsImg = described.substring(0,6)+"图片";    //头像填充

            Content content = new Content();
            content.setGoodsid(0);
            content.setGoodsname(goodsName);
            String[] split = price.split("￥");
            double prices = Double.parseDouble(split[1]);
            content.setPrice(prices);

            content.setDescribed(describe);
            content.setGoodsimg(goodsImg);
            //商品属于哪个用户，一共5个用户，随机生成id
            Random rand = new Random();
            int userid = rand.nextInt(5) + 1;

            content.setUserid(userid);
            content.setDateUp(new Date());
            content.setSellmassage(null);
            content.setStatus(1);
            content.setNums(0);

            goodsList.add(content);
        }
        return goodsList;
    }

    //测试一把
    public static void main(String[] args) throws IOException {
        ParseUtils parseUtils = new ParseUtils();
        ArrayList<Content> contents = parseUtils.parseJD("java");
        for (Content content : contents) {
            System.out.println(content);
        }
    }
}
