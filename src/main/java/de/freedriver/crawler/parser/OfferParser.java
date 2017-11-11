package de.freedriver.crawler.parser;

import java.io.IOException;
import java.util.Date;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import de.freedriver.crawler.CrawlerService;
import de.freedriver.models.Offer;
import de.freedriver.models.StarcarOffer;
import de.freedriver.repositories.OffersRepository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ou on 07.07.17.
 */
@Slf4j
@Getter
@Component
public class OfferParser {

    @Autowired
    private CrawlerService crawler;
    @Autowired
    private OffersRepository offersRepository;

    public Offer parseOffer(String url, String offerCss) {
        try {
            Elements elements = crawler.crawlHtmlElements(url, offerCss);
            return parseStarcars(elements, url);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

    private StarcarOffer parseStarcars(Elements elements, String url) {
        String id = UriComponentsBuilder.fromHttpUrl(url).build().getQueryParams().getFirst("offer");
        if (elements != null && elements.size() == 5) {
            return getStarcarOfferWithoutAge(elements, url, id);
        }
        if (elements != null && elements.size() == 6) {
            return getStarcarOfferWithAge(elements, url, id);
        }
        return null;
    }

    private StarcarOffer getStarcarOfferWithAge(final Elements elements, final String url, String id) {
        Offer existingOffer = offersRepository.getOfferById(id);
        Date now = new Date();

        String car = elements.get(0).html();
        String plate = elements.get(1).html();
        String age = elements.get(2).html();
        String date = elements.get(3).html();
        String startStation = elements.get(4).text();
        String returnStation = elements.get(5).text();
        Date createdAt = existingOffer != null ? existingOffer.getCreatedAt() : now;

        return StarcarOffer.builder()
                .id(id)
                .car(car)
                .age(age)
                .startStation(startStation)
                .returnStation(returnStation)
                .date(date)
                .plate(plate)
                .url(url)
                .active(true)
                .createdAt(createdAt)
                .updatedAt(now)
                .build();
    }

    private StarcarOffer getStarcarOfferWithoutAge(final Elements elements, final String url, String id) {
        Offer existingOffer = offersRepository.getOfferById(id);
        Date now = new Date();

        String car = elements.get(0).html();
        String plate = elements.get(1).html();
        String date = elements.get(2).html();
        String startStation = elements.get(3).text();
        String returnStation = elements.get(4).text();
        String age = "18 Jahre";
        Date createdAt = existingOffer != null ? existingOffer.getCreatedAt() : now;

        return StarcarOffer.builder()
                .id(id)
                .car(car)
                .age(age)
                .startStation(startStation)
                .returnStation(returnStation)
                .date(date)
                .plate(plate)
                .url(url)
                .active(true)
                .createdAt(createdAt)
                .updatedAt(now)
                .build();
    }
}

