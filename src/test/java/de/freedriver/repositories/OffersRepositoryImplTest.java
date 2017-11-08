package de.freedriver.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import de.freedriver.models.Offer;
import de.freedriver.models.StarcarOffer;

@RunWith(MockitoJUnitRunner.class)
public class OffersRepositoryImplTest extends Mockito {

    @InjectMocks
    private OffersRepositoryImpl offersRepositoryImpl;
    @Mock
    private OffersRepository offersRepository;

    @Test
    public void deactivateAllOffersForZeroOffers() {
        //given
        ArrayList<Offer> offers = new ArrayList<>();
        when(offersRepository.findAll()).thenReturn(offers);
        //when
        offersRepositoryImpl.deactivateAllOffers();
        //then
        assertThat(offers).isEmpty();
    }


    @Test
    public void deactivateAllOffersForOneOffer() {
        //given
        ArrayList<Offer> offers = new ArrayList<>();
        Offer offer = StarcarOffer.builder().active(true).build();
        offers.add(offer);
        when(offersRepository.findAll()).thenReturn(offers);
        //when
        offersRepositoryImpl.deactivateAllOffers();
        //then
        assertThat(offers.get(0).getActive()).isFalse();
    }

}