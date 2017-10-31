package de.freedriver;

import de.freedriver.crawler.collector.OfferCollectorService;
import de.freedriver.crawler.parser.OfferParser;
import de.freedriver.models.Offer;
import de.freedriver.models.Vendor;
import de.freedriver.models.Vendors;
import de.freedriver.repositories.OffersRepository;
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
    private OfferCollectorService offerCollectorService;
    @Autowired
    private OfferParser offerParser;
    @Autowired
    private VendorsParser vendorsParser;
    @Autowired
    private OffersRepository offersRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Vendors vendors = vendorsParser.getAllVendors();

        for (Vendor vendor : vendors.getVendors()) {
            HashSet<String> urls = offerCollectorService.crawlOfferUrls(vendor);
            for (String url : urls) {
                Offer offer = offerParser.parseOffer(url, vendor.getOfferCss());
                offersRepository.save(offer);
            }
        }

    }
}

