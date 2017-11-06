package de.freedriver.repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals

import de.freedriver.models.Offer;

@RunWith(MockitoJUnitRunner.class)
class OffersRepositoryTest extends Mockito {

    @InjectMocks
    OffersRepository offersRepository;

    @Test
    public void testFindAllOffers(){
        //given


        //when
        List<Offer> allOffers = offersRepository.findAll();

        //then
        assertEquals(allOffers.size(), 0);
    }

}