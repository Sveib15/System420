package Database;

import Database.BlogPost;
import Database.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T17:02:18")
@StaticMetamodel(Blog.class)
public class Blog_ { 

    public static volatile ListAttribute<Blog, BlogPost> blogPostList;
    public static volatile ListAttribute<Blog, User> userList;
    public static volatile SingularAttribute<Blog, Integer> blogId;

}