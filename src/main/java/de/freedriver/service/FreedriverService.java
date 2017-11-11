package de.freedriver.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.freedriver.crawler.collector.OfferCollectorService;
import de.freedriver.crawler.parser.OfferParser;
import de.freedriver.models.Offer;
import de.freedriver.models.Vendor;
import de.freedriver.models.Vendors;
import de.freedriver.repositories.OffersRepository;
import de.freedriver.util.VendorsParser;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FreedriverService {

    @Autowired
    private OfferCollectorService offerCollectorService;
    @Autowired
    private OfferParser offerParser;
    @Autowired
    private VendorsParser vendorsParser;
    @Autowired
    private OffersRepository offersRepository;

    public void crawlAllVendors() {
        Vendors vendors = vendorsParser.getAllVendors();

        for (Vendor vendor : vendors.getVendors()) {
            HashSet<String> urls = offerCollectorService.crawlOfferUrls(vendor);
            offersRepository.deactivateAllOffers();
            for (String url : urls) {
                Offer offer = offerParser.parseOffer(url, vendor.getOfferCss());
                log.info("New Offer: {}", offer.toString());
                offersRepository.save(offer);
            }
        }

    }
}
