package crawler.parser;

import com.google.common.annotations.VisibleForTesting;
import crawler.Crawler;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.Offer;
import models.StarcarOffer;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by ou on 07.07.17.
 */
@Builder
@Slf4j
@Getter
public class OfferParser {

    @VisibleForTesting
    String offerType;
    private String url;
    private String offerCss;
    private Crawler crawler;

    public Offer parseOffer() {
        if (crawler == null) crawler = Crawler.builder().build();
        try {
            Elements elements = crawler.crawlHtmlElements(url, offerCss);
            switch (getOfferType()) {
                default:
                    return parseStarcars(elements);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

    private StarcarOffer parseStarcars(Elements elements) {
        if (elements != null && elements.size() > 0) {
            String car;
            String plate;
            String age = "18 Jahre";
            String date;
            String startStation;
            String returnStation;
            if (elements.size() == 5) { // no age field
                car = elements.get(0).html();
                plate = elements.get(1).html();
                date = elements.get(2).html();
                startStation = elements.get(3).html();
                returnStation = elements.get(4).html();
            } else { // custom age field
                car = elements.get(0).html();
                plate = elements.get(1).html();
                age = elements.get(2).html();
                date = elements.get(3).html();
                startStation = elements.get(4).html();
                returnStation = elements.get(5).html();
            }
            StarcarOffer offer = StarcarOffer.builder().car(car).age(age).startStation(startStation).returnStation(returnStation).date(date).plate(plate).url(url).build();
            log.info("Starcar Offer {}", offer);
            return offer;
        }
        return null;
    }

}
