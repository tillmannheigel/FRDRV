package crawler.collector;

import crawler.Crawler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class OfferCollector {

    private String url;
    private String cssQuery;
    private Crawler crawler;

    public OfferCollector(String url, String cssQuery) {
        this.url = url;
        this.cssQuery = cssQuery;
        crawler = new Crawler();
    }

    public String[] collectOffers() {
        try {
            Elements elements = crawler.crawlHtmlElements(url, cssQuery);
            return elements.stream()
                    .map(this::extractLinks)
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    private String extractLinks(Element element) {
        return element.select("a[href]").attr("href");
    }
}
