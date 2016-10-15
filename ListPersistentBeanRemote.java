
package pojo;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author torar_000
 */
@Remote
public interface ListPersistentBeanRemote {
    
    public void addPerson(Person p);
    
    public List<Person> getPersons();
}
