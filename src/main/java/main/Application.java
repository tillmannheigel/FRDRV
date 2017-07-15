package main;

import com.google.common.base.Joiner;
import crawler.collector.OfferCollector;
import crawler.parser.OfferParser;
import lombok.extern.slf4j.Slf4j;
import models.Vendor;
import models.Vendors;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import util.VendorsParser;

import java.util.HashSet;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        VendorsParser parser = new VendorsParser();
        Vendors vendors = parser.getAllVendors();
        for (Vendor vendor : vendors.getVendors()) {
            OfferCollector offerCollector = OfferCollector
                    .builder()
                    .cssQuery(vendor.getCss())
                    .url(vendor.getUrl())
                    .build();
            HashSet<String> offersSet = offerCollector.collectOffers();
            String setToString = Joiner.on("\n").skipNulls().join(offersSet);
            log.info("load offers of vendor: {}\n{}", vendor.getTitle(), setToString);
            log.info("count: {}", offersSet.size());
            String[] offersArray = offersSet.stream().toArray(String[]::new);
            for (int j = 0; j < offersSet.size(); j++) {
                OfferParser.builder().offerCss(vendor.getOfferCss()).offerType(vendor.getTitle()).url(offersArray[j]).build().parseOffer();
                j++;
            }
        }
    }
}
