package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Rol;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-03-28T12:59:33")
@StaticMetamodel(User.class)
public class User_ extends Person_ {

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, Rol> rol;

}