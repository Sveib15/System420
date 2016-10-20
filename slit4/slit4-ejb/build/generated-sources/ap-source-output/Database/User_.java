package Database;

import Database.Blog;
import Database.Delivery;
import Database.SemesterPlan;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T17:02:18")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, Delivery> deliveryList;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, Integer> phoneNumber;
    public static volatile SingularAttribute<User, Integer> rights;
    public static volatile SingularAttribute<User, String> title;
    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile SingularAttribute<User, Blog> blogId;
    public static volatile SingularAttribute<User, SemesterPlan> semesterPlanId;
    public static volatile SingularAttribute<User, String> email;

}