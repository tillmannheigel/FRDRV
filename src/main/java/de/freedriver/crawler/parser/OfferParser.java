package de.freedriver.crawler.parser;

import com.google.common.annotations.VisibleForTesting;
import de.freedriver.crawler.CrawlerService;
import de.freedriver.models.Offer;
import de.freedriver.models.StarcarOffer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by ou on 07.07.17.
 */
@Slf4j
@Getter
@Component
public class OfferParser {

    @VisibleForTesting
    String offerType;
    @Autowired
    private CrawlerService crawler;

    public Offer parseOffer(String url, String offerCss) {
        try {
            Elements elements = crawler.crawlHtmlElements(url, offerCss);
            log.debug(String.valueOf(elements));
            return parseStarcars(elements, url);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

    private StarcarOffer parseStarcars(Elements elements, String url) {
        if (elements != null) {
            if (elements.size() == 5) { // no age field
                String car = elements.get(0).html();
                String plate = elements.get(1).html();
                String date = elements.get(2).html();
                String startStation = elements.get(3).html();
                String returnStation = elements.get(4).html();
                String age = "18 Jahre";
                return StarcarOffer.builder().car(car).age(age).startStation(startStation).returnStation(returnStation).date(date).plate(plate).url(url)
                        .build();
            }
            if (elements.size() == 6) { // custom age field
                String car = elements.get(0).html();
                String plate = elements.get(1).html();
                String age = elements.get(2).html();
                String date = elements.get(3).html();
                String startStation = elements.get(4).html();
                String returnStation = elements.get(5).html();
                return StarcarOffer.builder().car(car).age(age).startStation(startStation).returnStation(returnStation).date(date).plate(plate).url(url)
                        .build();
            }
        }
        return null;
    }
}

