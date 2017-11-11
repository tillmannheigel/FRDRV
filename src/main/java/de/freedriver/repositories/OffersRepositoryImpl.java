package de.freedriver.repositories;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.WriteResult;

import de.freedriver.models.Offer;

public class OffersRepositoryImpl implements OffersRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override public WriteResult deactivateAllOffers() {
        return mongoTemplate.updateMulti(query(where("_id").exists(true)), update("active", false), Offer.class);
    }
}
