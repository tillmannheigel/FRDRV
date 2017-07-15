package de.freedriver;

import com.google.common.base.Joiner;
import de.freedriver.crawler.collector.OfferCollector;
import de.freedriver.crawler.parser.OfferParser;
import de.freedriver.models.Vendor;
import de.freedriver.models.Vendors;
import de.freedriver.service.KafkaMessengerService;
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
    KafkaMessengerService kafkaMessengerService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Hello Kafka: {}", kafkaMessengerService.toString());
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
