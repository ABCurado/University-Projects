package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.framework.persistence.repositories.Repository;

/**
 * Created by nuno on 21/03/16.
 */
public interface UserRepository extends Repository<SystemUser, Username> {

}
