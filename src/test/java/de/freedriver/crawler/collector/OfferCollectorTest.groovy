package de.freedriver.crawler.collector

import de.freedriver.crawler.Crawler
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
    OfferCollector offerCollector
    @Mock
    Crawler crawler

    @Test
    void testCollectOffersForEmptyModelHasSize0() {
        //given
        Elements elements = new Elements(0)

        //when
        when(crawler.crawlHtmlElements(null, null)).thenReturn(elements)

        //then
        assertEquals(offerCollector.collectOffers().size(), 0)
    }
}
