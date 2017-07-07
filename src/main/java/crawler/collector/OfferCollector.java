package crawler.collector;

import crawler.Crawler;
import lombok.Builder;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Builder
public class OfferCollector {

    private String url;
    private String cssQuery;
    private Crawler crawler;

    public HashSet<String> collectOffers() {
        if (crawler == null) crawler = Crawler.builder().build();
        try {
            Elements elements = crawler.crawlHtmlElements(url, cssQuery);
            String[] array = elements.stream()
                    .map(this::extractLinks)
                    .toArray(String[]::new);
            return new HashSet<>(Arrays.asList(array));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }

    private String extractLinks(Element element) {
        return element.select("a[href]").attr("href");
    }
}
