package de.freedriver.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import de.freedriver.models.Offer;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Skip {

}
