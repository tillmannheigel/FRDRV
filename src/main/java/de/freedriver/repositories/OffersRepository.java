package de.freedriver.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.freedriver.models.Offer;

public interface OffersRepository extends MongoRepository<Offer, String>, OffersRepositoryCustom {

    //Offers (plural)
    List<Offer> getOffersByStartStationIsContaining(String startStation);

    //Offer (singular)
    Offer getOfferById(String id);

}
