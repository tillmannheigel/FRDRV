package de.freedriver.crawler;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CrawlerService {

    public Elements crawlHtmlElements(String url, String cssQuery) throws IOException {
        Document doc = Jsoup.connect(url).timeout(10000).get();
        return doc.select(cssQuery);
    }

}
