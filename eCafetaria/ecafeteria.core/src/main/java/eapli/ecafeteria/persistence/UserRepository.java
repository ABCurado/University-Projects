package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.framework.persistence.repositories.Repository;
import java.util.List;

/**
 * Created by nuno on 21/03/16.
 */
public interface UserRepository extends Repository<SystemUser, Username> {
    
    /**
     * Returns a list of active users.
     * 
     * @return List of users that are currently active in the system.
     */
    public List<SystemUser> activeUsers();
    
}
