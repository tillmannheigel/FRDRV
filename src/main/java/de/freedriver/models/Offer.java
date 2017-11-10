package de.freedriver.models;

import java.util.Date;

/**
 * Created by ou on 07.07.17.
 */
public interface Offer {

    String getId();

    void setId(final String id);

    String getCar();
    
    void setCar(final String car);

    String getPlate();

    void setPlate(final String plate);

    String getDate();

    void setDate(final String date);

    String getAge();

    void setAge(final String age);

    String getStartStation();

    void setStartStation(final String startStation);

    String getReturnStation();

    void setReturnStation(final String returnStation);

    String getUrl();

    void setUrl(final String url);

    Date getCreatedAt();

    void setCreatedAt(final Date createdAt);

    Date getUpdatedAt();

    void setUpdatedAt(final Date updatedAt);

    Boolean getActive();

    void setActive(final Boolean active);
}
