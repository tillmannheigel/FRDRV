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

import static org.junit.Assert.assertNotNull;
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
        Offer offer = offerParser.parseOffer("mockUrl", "mockCss");
        //then
        assertNull(offer);
    }

    @Test
    public void parseExistingOffer() throws Exception {
        //given
        String mockUrl = "mockUrl";
        String mockCss = "mockCss";
        Element element = mock(Element.class);
        Elements elements = mock(Elements.class);
        offerParser.offerType = "abc";
        when(crawler.crawlHtmlElements(mockUrl, mockCss)).thenReturn(elements);
        when(elements.size()).thenReturn(5);
        when(element.html()).thenReturn("mockHtml");
        for (int i = 0; i < 6; i++) {
            when(elements.get(i)).thenReturn(element);
        }

        //when
        Offer offer = offerParser.parseOffer(mockUrl, mockCss);

        //then
        assertNotNull(offer);
    }
}
