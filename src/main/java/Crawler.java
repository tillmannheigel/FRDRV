import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by tillmannheigel on 29.12.16.
 */
public class Crawler {

    public void crawlHtmlElements(String url, String cssQuery) throws IOException{
        Document doc = Jsoup.connect(url).get();
        Elements listItem = doc.select(cssQuery);
    }

}
