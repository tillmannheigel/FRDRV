package de.freedriver.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StarcarOffer implements Offer {

    String car;
    String plate;
    String date;
    String age;
    String startStation;
    String returnStation;
    String url;

}
