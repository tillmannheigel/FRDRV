package de.freedriver.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StarcarOffer extends Offer {

    String car;
    String plate;
    String date;
    String age;
    String startStation;
    String returnStation;
    String url;

}
