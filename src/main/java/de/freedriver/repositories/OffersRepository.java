package de.freedriver.repositories;

import java.util.List;

import de.freedriver.models.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OffersRepository extends MongoRepository<Offer, String>, OffersRepositoryCustom {

    List<Offer> findOffersByStartAt();
}
