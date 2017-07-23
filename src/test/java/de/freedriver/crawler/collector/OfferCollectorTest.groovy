package de.freedriver.crawler.collector

import de.freedriver.crawler.CrawlerService
import de.freedriver.service.KafkaMessengerService
import org.jsoup.select.Elements
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

import static org.junit.Assert.assertEquals

@RunWith(MockitoJUnitRunner.class)
class OfferCollectorTest extends Mockito {

    @InjectMocks
    OfferCollectorService offerCollectorService
    @Mock
    CrawlerService crawler
    @Mock
    KafkaMessengerService kafkaMessengerService

    @Test
    void testCollectOffersForEmptyModel() {
        //given
        Elements elements = new Elements(0)

        //when
        when(crawler.crawlHtmlElements(null, null)).thenReturn(elements)

        //then
        assertEquals(offerCollectorService.collectOffers(null, null).size(), 0)
    }

    @Test
    void testCollectOffersCatchesIOException() {
        //given
        IOException ioException = new IOException()

        //when
        when(crawler.crawlHtmlElements(null, null)).thenThrow(ioException)

        //then
        assertEquals(offerCollectorService.collectOffers(null, null).size(), 0)
    }

}