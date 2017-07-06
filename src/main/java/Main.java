import crawler.collector.OfferCollector;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting");
        OfferCollector offers = new OfferCollector("https://www.starcar.de/specials/kostenlos-mieten/", ".togglelist-content-item");
        offers.collectOffers();
    }
}
