package Database;

import Database.ModulePlan;
import Database.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T17:02:18")
@StaticMetamodel(SemesterPlan.class)
public class SemesterPlan_ { 

    public static volatile ListAttribute<SemesterPlan, User> userList;
    public static volatile ListAttribute<SemesterPlan, ModulePlan> modulePlanList;
    public static volatile SingularAttribute<SemesterPlan, Integer> semesterPlanId;

}