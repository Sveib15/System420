package Database;

import Database.Delivery;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T17:02:18")
@StaticMetamodel(File.class)
public class File_ { 

    public static volatile SingularAttribute<File, Delivery> deliveryId;
    public static volatile SingularAttribute<File, byte[]> content;
    public static volatile SingularAttribute<File, Integer> fileId;

}