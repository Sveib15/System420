
package slit2_client;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import pojo.Person;



/**
 *
 * @author torar_000
 */
public class Main {
    
    @EJB
    //@PersistenceUnit(unitName = "Person")
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        
        Person p1 = new Person(1, "Test1");
       
        Person p2 = new Person(2, "Test2");
        
        emf = Persistence.createEntityManagerFactory("Test");
        
        EntityManager em = emf.createEntityManager();
        
        em.persist(p1);
        
        
        
    }
    
}