package de.freedriver.crawler.parser;

import de.freedriver.crawler.CrawlerService;
import de.freedriver.models.Offer;
import de.freedriver.repositories.OffersRepository;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class OfferParserTest extends Mockito {

    static String MOCK_URL = "http://mock.url/?offer=123";
    @InjectMocks
    private OfferParser offerParser;
    @Mock
    private CrawlerService crawler;
    @Mock
    private OffersRepository offersRepository;

    @Test
    public void parseEmptyOffer() throws Exception {
        //given
        Elements elements = new Elements(0);
        //when
        Offer offer = offerParser.parseOffer(MOCK_URL, "mockCss");
        //then
        assertNull(offer);
    }

    @Test
    public void parseExistingOfferWithoutAge() throws Exception {
        //given
        String mockCss = givenOfferWithLength(5);

        //when
        Offer offer = offerParser.parseOffer(MOCK_URL, mockCss);

        //then
        assertNotNull(offer);
    }

    @Test
    public void parseExistingOfferWithAge() throws Exception {
        //given
        String mockCss = givenOfferWithLength(6);

        //when
        Offer offer = offerParser.parseOffer(MOCK_URL, mockCss);

        //then
        assertNotNull(offer);
    }

    private String givenOfferWithLength(int value) throws IOException {
        String mockCss = "mockCss";
        Element element = mock(Element.class);
        Elements elements = mock(Elements.class);
        when(crawler.crawlHtmlElements(MOCK_URL, mockCss)).thenReturn(elements);
        when(elements.size()).thenReturn(value);
        when(element.html()).thenReturn("mockHtml");
        for (int i = 0; i < 6; i++) {
            when(elements.get(i)).thenReturn(element);
        }
        return mockCss;
    }
}
