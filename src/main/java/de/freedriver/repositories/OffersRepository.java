package de.freedriver.repositories;

import de.freedriver.models.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OffersRepository extends MongoRepository<Offer, String> {

}
