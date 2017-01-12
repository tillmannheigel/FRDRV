package model;

import lombok.Data;
import lombok.Value;

/**
 * Created by tillmannheigel on 30.12.16.
 */
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
