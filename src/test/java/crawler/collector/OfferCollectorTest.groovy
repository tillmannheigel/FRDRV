package crawler.collector

import crawler.Crawler
import org.jsoup.select.Elements
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

import static org.junit.Assert.assertArrayEquals

@RunWith(MockitoJUnitRunner.class)
class OfferCollectorTest extends Mockito {

    @InjectMocks
    OfferCollector offerCollector
    @Mock
    Crawler crawler

    @Test
    void testCollectOffersForEmptyModel() {
        //given
        Elements elements = new Elements(0)
        when(crawler.crawlHtmlElements(null, null)).thenReturn(elements)

        //when

        //then
        assertArrayEquals(offerCollector.collectOffers(), new String[0])
    }

}
