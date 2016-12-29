import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by tillmannheigel on 29.12.16.
 */
public class Starcar {

    private String URL = "https://www.starcar.de/specials/kostenlos-mieten/";
    private String CSS_QUERY = ".togglelist-content-item";

    public void crawlStarcars() {
        try {
            Elements elements = new Crawler().crawlHtmlElements(URL, CSS_QUERY);
            Object[] links = elements.stream()
                    .map(this::extractLinks)
                    .map(this::crawlStarcar)
                    .toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractLinks(Element element) {
        String link = element.select("a[href]").attr("href");
        System.out.println(link);
        return link;
    }

    private Element crawlStarcar(String url) {
        Element element = null;
        try {
            element = new Crawler().crawlHtmlElements(url, "h4").first();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(element.toString());
        return element;
    }
}
