package crawler.parser;

import crawler.Crawler;
import models.Offer;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class OfferParserTest extends Mockito {

    @InjectMocks
    OfferParser offerParser;
    @Mock
    Crawler crawler;


    @Test
    public void parseOffer() throws Exception {
        //given
        Elements elements = new Elements(0);
        offerParser.offerType = "abc";
        //when
        when(crawler.crawlHtmlElements(null, null)).thenReturn(elements);
        Offer offer = offerParser.parseOffer();
        //then
        assertNull(offer);
    }

}