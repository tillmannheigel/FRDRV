package de.freedriver.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import de.freedriver.models.Offer;

public class OffersRepositoryImpl implements OffersRepositoryCustom {

    @Autowired
    OffersRepository offersRepository;

    @Override public void deactivateAllOffers() {
        List<Offer> offers = offersRepository.findAll();
        for (Offer offer : offers) {
            offer.setActive(false);
            offersRepository.save(offer);
        }
    }
}
