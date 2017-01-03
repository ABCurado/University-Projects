package eapli.ecafeteria.domain.cafeteria;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * TODO is this a DDD entity, value object or aggregate root?
 *
 * TODO javadoc
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
@Entity
public class Cafeteria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cafeteria)) {
            return false;
        }
        Cafeteria other = (Cafeteria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eapli.ecafeteria.domain.cafeteria.Cafeteria[ id=" + id + " ]";
    }

}
