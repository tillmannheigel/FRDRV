package de.freedriver.repositories;

import com.mongodb.WriteResult;

public interface OffersRepositoryCustom {

    WriteResult deactivateAllOffers();
}
