package de.freedriver.models;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StarcarOffer implements Offer {

    @Id
    private String id;
    private String car;
    private String plate;
    private String date;
    private String age;
    private String startStation;
    private String returnStation;
    private String url;
    private Date createdAt;
    private Date updatedAt;
    private Boolean active;

}
