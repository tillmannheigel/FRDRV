package de.freedriver;

import de.freedriver.crawler.collector.OfferCollectorService;
import de.freedriver.crawler.parser.OfferParser;
import de.freedriver.models.Vendor;
import de.freedriver.models.Vendors;
import de.freedriver.util.VendorsParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashSet;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class Application implements ApplicationRunner {

    @Autowired
    OfferCollectorService offerCollectorService;
    @Autowired
    OfferParser offerParser;
    @Autowired
    VendorsParser vendorsParser;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Vendors vendors = vendorsParser.getAllVendors();

        for (Vendor vendor : vendors.getVendors()) {
            HashSet<String> offers = offerCollectorService.collectOffers(vendor);
            for (String offer : offers) {
                String parsedOffer = offerParser.parseOffer(offer, vendor.getOfferCss());
                log.info(parsedOffer);
            }
        }

    }
}

