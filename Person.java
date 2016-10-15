
package pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 *
 * @author torar_000
 */

@Entity
public class Person implements Serializable{
    
    @Id
    @Column
    private int p_id;
    @Column
    private String navn;
    
    public Person(){
        
    }
    public Person (int p_id, String navn){
        this.p_id = p_id;
        this.navn = navn;
        
    }

    public int getID(){
        return p_id;
    }
    
    public void setID(int id){
        this.p_id = id;
    }
    
    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
    
    
    
}