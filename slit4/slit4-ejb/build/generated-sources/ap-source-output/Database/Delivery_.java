package Database;

import Database.File;
import Database.Module;
import Database.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T17:02:18")
@StaticMetamodel(Delivery.class)
public class Delivery_ { 

    public static volatile SingularAttribute<Delivery, Integer> deliveryId;
    public static volatile SingularAttribute<Delivery, Date> dateApproved;
    public static volatile SingularAttribute<Delivery, String> comment;
    public static volatile SingularAttribute<Delivery, Integer> queNumber;
    public static volatile SingularAttribute<Delivery, Module> moduleId;
    public static volatile SingularAttribute<Delivery, User> userId;
    public static volatile ListAttribute<Delivery, File> fileList;
    public static volatile SingularAttribute<Delivery, Boolean> status;
    public static volatile SingularAttribute<Delivery, Date> dateDelivered;

}