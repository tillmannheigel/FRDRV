package models;

import lombok.Data;

@Data
public class StarcarModel {
    String car;
    String group;
    String type;
    String startDate;
    String returnDate;
    String startStationId;
    String returnStationId;
}
