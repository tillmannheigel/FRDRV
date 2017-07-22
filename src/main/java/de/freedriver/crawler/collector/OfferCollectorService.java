package de.freedriver.crawler.collector;

import de.freedriver.crawler.CrawlerService;
import de.freedriver.service.KafkaMessengerService;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class OfferCollectorService {

    @Autowired
    private KafkaMessengerService kafkaMessengerService;
    @Autowired
    private CrawlerService crawlerService;

    public HashSet<String> collectOffers(String url, String cssQuery) {
        try {
            Elements elements = crawlerService.crawlHtmlElements(url, cssQuery);
            return elements.stream()
                    .map(this::extractLinks)
                    .collect(Collectors.toCollection(HashSet::new));
        } catch (IOException e) {
            kafkaMessengerService.sendToExceptionTopic(this.toString(), e.getMessage());
            e.printStackTrace();
        }
        return new HashSet<>();
    }

    private String extractLinks(Element element) {
        return element.select("a[href]").attr("href");
    }
}
