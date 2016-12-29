import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by tillmannheigel on 29.12.16.
 */
public class Starcar {

    private String URL = "https://www.starcar.de/specials/kostenlos-mieten/";
    private String CSS_QUERY = ".togglelist-content-item";

    public void crawStarcars(){
        try {
            Elements elements = new Crawler().crawlHtmlElements(URL, CSS_QUERY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
