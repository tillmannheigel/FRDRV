package de.freedriver.crawler.parser;

import de.freedriver.crawler.CrawlerService;
import de.freedriver.models.Offer;
import org.jsoup.nodes.Element;
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
    CrawlerService crawler;


    @Test
    public void parseEmptyOffer() throws Exception {
        //given
        Elements elements = new Elements(0);
        offerParser.offerType = "abc";
        //when
        when(crawler.crawlHtmlElements(null, null)).thenReturn(elements);
        Offer offer = offerParser.parseOffer();
        //then
        assertNull(offer);
    }

    @Test
    public void parseExistingOffer() throws Exception {
        //given
        Element element = mock(Element.class);
        Elements elements = new Elements(1);
        elements.set(1, element);
        offerParser.offerType = "abc";
        //when
        when(crawler.crawlHtmlElements(null, null)).thenReturn(elements);
        Offer offer = offerParser.parseOffer();
        //then
        assertNull(offer);
    }

}