package com.xinwen.crawl;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * com.xinwen.crawl
 *
 * @author 庄先生
 * @date 2021/1/30
 */
public class WebMagicCrawl implements PageProcessor {

    private Site site = Site.me()
            .setCharset("UTF-8")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36")
            .setRetryTimes(3) //重试次数
            .setCycleRetryTimes(3)//循环重试
//            .setSleepTime(3000)
            .setTimeOut(15000); //超时时间

    @Override
    public void process(Page page) {

        List<String> hrefs = page.getHtml().css("div.flex.grid-list__grid.xs12 div.flex.shrink app-grid__section.app-grid__section--horizontal a.app-grid__image-container.app-grid__image-container--flat", "href").all();

        System.out.println(hrefs);


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new WebMagicCrawl())
//                .addUrl("https://www.881903.com/news/recent")
                .addUrl("https://www.wenweipo.com/immed")
//                .thread(5)
                .addPipeline(new MyPipeline())
                .run();
    }

}
