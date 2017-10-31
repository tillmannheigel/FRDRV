package de.freedriver.crawler.collector

import de.freedriver.crawler.CrawlerService
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

    @Test
    void testCollectOffersForEmptyModel() {
        //given
        Elements elements = new Elements(0)

        //when
        when(crawler.crawlHtmlElements(null, null)).thenReturn(elements)

        //then
        assertEquals(offerCollectorService.crawlOfferUrls(null, null).size(), 0)
    }

    @Test
    void testCollectOffersCatchesIOException() {
        //given
        IOException ioException = new IOException()

        //when
        when(crawler.crawlHtmlElements(null, null)).thenThrow(ioException)

        //then
        assertEquals(offerCollectorService.crawlOfferUrls(null, null).size(), 0)
    }

}
