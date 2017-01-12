package crawler;

import crawler.Crawler;
import model.StarcarModel;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by tillmannheigel on 29.12.16.
 */
public class StarcarCrawler {

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

    private StarcarModel crawlStarcar(String url) {
        Element element = null;
        try {
            element = new Crawler().crawlHtmlElements(url, "#form-booking-kostenlos-mieten").first();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StarcarModel starcar = new StarcarModel();

        starcar.setCar(element.getElementsByAttributeValue("name", "reservation[vehicleDescription]").attr("value"));
        starcar.setGroup(element.getElementsByAttributeValue("name", "reservation[group]").attr("value"));
        starcar.setType(element.getElementsByAttributeValue("name", "reservation[type]").attr("value"));
        //starcar.setStartDate(element.getElementsByAttributeValue("name", "reservation[vehicleDescription]").attr("value"));
        //starcar.setReturnDate(element.getElementsByAttributeValue("name", "reservation[vehicleDescription]").attr("value"));
        starcar.setStartStationId(element.getElementsByAttributeValue("name", "reservation[station]").attr("value"));
        starcar.setReturnStationId(element.getElementsByAttributeValue("name", "reservation[return_station]").attr("value"));

        System.out.println(starcar.toString());

        return starcar;
    }
}
