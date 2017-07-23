package de.freedriver.crawler;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.jsoup.select.Elements;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ou on 22.07.17.
 */
public class CrawlerServiceTest extends Mockito {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8081);

    @Test
    public void crawlHtmlElementsWithoutMatchingSelector() throws Exception {
        CrawlerService crawlerService = new CrawlerService();
        stubFor(get(urlEqualTo("/offers/"))
                .withHeader("Accept", equalTo("text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/html")
                        .withBody("<body>Empty content</body>")));

        Elements elements = crawlerService.crawlHtmlElements("http://localhost:8081/offers/", ".select");

        assertTrue(elements.size() == 0);

    }

    @Test
    public void crawlHtmlElementsWithMatchingSelector() throws Exception {
        CrawlerService crawlerService = new CrawlerService();
        stubFor(get(urlEqualTo("/offers/"))
                .withHeader("Accept", equalTo("text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/html")
                        .withBody("<body><div class='select'>Empty content</div></body>")));

        Elements elements = crawlerService.crawlHtmlElements("http://localhost:8081/offers/", ".select");

        assertTrue(elements.size() > 0);

    }
}
