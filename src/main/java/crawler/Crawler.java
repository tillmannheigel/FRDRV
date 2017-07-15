package crawler;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

@Builder
@Slf4j
public class Crawler {

    public Elements crawlHtmlElements(String url, String cssQuery) throws IOException {
        Document doc = Jsoup.connect(url).timeout(10000).get();
        return doc.select(cssQuery);
    }

}
