package de.freedriver;

import com.google.common.base.Joiner;
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        VendorsParser parser = new VendorsParser();
        Vendors vendors = parser.getAllVendors();
        for (Vendor vendor : vendors.getVendors()) {
            HashSet<String> offersSet = offerCollectorService.collectOffers(vendor.getUrl(), vendor.getCss());
            String setToString = Joiner.on("\n").skipNulls().join(offersSet);
            log.debug("load offers of vendor: {}\n{}", vendor.getTitle(), setToString);
            log.debug("count: {}", offersSet.size());
            offersSet.stream().map(s -> offerParser.parseOffer(s, vendor.getOfferCss())).forEach(s -> log.debug(s));
        }
    }
}

