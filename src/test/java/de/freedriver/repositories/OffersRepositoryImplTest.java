package de.freedriver.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.WriteResult;

import de.freedriver.models.Offer;

@RunWith(MockitoJUnitRunner.class)
public class OffersRepositoryImplTest extends Mockito {

    @InjectMocks
    private OffersRepositoryImpl offersRepositoryImpl;
    @Mock
    private MongoTemplate mongoTemplate;

    @Test
    public void deactivateAllOffersTest() {
        //given
        WriteResult writeResult = mock(WriteResult.class);
        when(mongoTemplate.updateMulti(query(where("_id").exists(true)), update("active", false), Offer.class)).thenReturn(writeResult);
        //when
        WriteResult result = offersRepositoryImpl.deactivateAllOffers();
        //then
        //ToDo: What else can be tested here?
        assertThat(result).isEqualTo(writeResult);
    }

}