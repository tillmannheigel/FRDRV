import crawler.collector.OfferCollector;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting");
        OfferCollector offers = new OfferCollector("https://www.starcar.de/specials/kostenlos-mieten/", ".togglelist-content-item");
        String[] starCarLinks = offers.collectOffers();
        log.info("Starcar Offers: {}", Arrays.toString(starCarLinks));
    }
}
