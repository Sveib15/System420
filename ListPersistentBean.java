
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import pojo.ListPersistentBeanRemote;
import pojo.Person;

/**
 *
 * @author torar_000
 */
@Stateless
public class ListPersistentBean implements ListPersistentBeanRemote{
    
    @PersistenceContext
    private EntityManager em;
    
    public void addPerson(Person p){
        
        em.persist(p);
    }
    
    public List<Person> getPersons(){
        return em.createQuery("FROM Person").getResultList();
    }
    
    
}