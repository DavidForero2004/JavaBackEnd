package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.User;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-04-07T13:43:44")
@StaticMetamodel(Secretary.class)
public class Secretary_ extends Person_ {

    public static volatile SingularAttribute<Secretary, String> zone;
    public static volatile SingularAttribute<Secretary, Integer> id;
    public static volatile SingularAttribute<Secretary, User> user;

}