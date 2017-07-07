import com.google.common.base.Joiner;
import crawler.collector.OfferCollector;
import crawler.parser.OfferParser;
import lombok.extern.slf4j.Slf4j;
import models.Vendor;
import models.Vendors;
import util.VendorsParser;

import java.util.HashSet;

@Slf4j
public class Main {

    public static void main(String[] args) {
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
