package de.freedriver.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.freedriver.models.Offer;

public interface OffersRepository extends MongoRepository<Offer, String>, OffersRepositoryCustom {

    List<Offer> findOffersByStartStation();

    Offer getOfferById(String id);

}
