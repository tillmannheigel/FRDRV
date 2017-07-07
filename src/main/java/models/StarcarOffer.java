package models;

import lombok.Builder;

@Builder
public class StarcarOffer extends Offer {

    String car;
    String plate;
    String date;
    String age;
    String startStation;
    String returnStation;
    String url;

}
