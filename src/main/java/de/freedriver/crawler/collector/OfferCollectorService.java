package de.freedriver.crawler.collector;

import de.freedriver.crawler.CrawlerService;
import de.freedriver.service.KafkaMessengerService;
import lombok.Data;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Data
@Component
public class OfferCollectorService {

    @Autowired
    KafkaMessengerService kafkaMessengerService;
    @Autowired
    CrawlerService crawlerService;

    public HashSet<String> collectOffers(String url, String cssQuery) {
        try {
            Elements elements = crawlerService.crawlHtmlElements(url, cssQuery);
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
