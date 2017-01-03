package eapli.ecafeteria.domain.mealbooking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;

/**
 * Created by Nuno Bettencourt [NMB] on 03/04/16.
 */
public class CafeteriaUserTest {

    private final String anEmail = new String("a@a.en");
    private final String anotherEmail = new String("a@a.en");

    @Test
    public void ensureCafeteriaUserEqualsPassesForTheSameMecanographicNumber() throws Exception {
        boolean expected = true;

        final String aMecanographicNumber = new String("abc");
        final String anotherMecanographicNumber = new String("abc");

        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);

        final SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", this.anEmail,
                roles);

        final OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

        final CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
                aMecanographicNumber);
        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
                anotherMecanographicNumber);
        expected = aCafeteriaUser.equals(anotherCafeteriaUser);

        assertTrue(expected);
    }

    @Test
    public void ensureCafeteriaUserEqualsFailsForDifferenteMecanographicNumber() throws Exception {
        boolean expected = false;

        final String aMecanographicNumber = new String("abc");
        final String anotherMecanographicNumber = new String("qwe");

        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);

        final SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", this.anEmail,
                roles);

        final OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

        final CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
                aMecanographicNumber);
        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
                anotherMecanographicNumber);

        expected = aCafeteriaUser.equals(anotherCafeteriaUser);

        assertFalse(expected);
    }

    @Test
    public void ensureCafeteriaUserEqualsAreTheSameForTheSameInstance() throws Exception {
        boolean expected = true;

        final CafeteriaUser aCafeteriaUser = new CafeteriaUser();

        expected = aCafeteriaUser.equals(aCafeteriaUser);

        assertTrue(expected);
    }

    @Test
    public void ensureCafeteriaUserEqualsFailsForDifferenteObjectTypes() throws Exception {
        boolean expected = false;

        final String aMecanographicNumber = new String("abc");

        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);

        final SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", this.anEmail,
                roles);

        final OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

        final CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
                aMecanographicNumber);

        final Set<RoleType> systemUserRoles = new HashSet<RoleType>();
        systemUserRoles.add(RoleType.Admin);

        final SystemUser systemUser = new SystemUser("userName", "password", "firsName", "lastName", this.anEmail,
                systemUserRoles);

        expected = aCafeteriaUser.equals(systemUser);

        assertFalse(expected);
    }

    @Test
    public void ensureCafeteriaUserIsTheSameAsItsInstance() throws Exception {
        boolean expected = true;

        final String aMecanographicNumber = new String("abc");

        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);

        final SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", this.anEmail,
                roles);


        final OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

        final CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
                aMecanographicNumber);

        expected = aCafeteriaUser.sameAs(aCafeteriaUser);

        assertTrue(expected);
    }

    @Test
    public void ensureTwoCafeteriaUserWithDifferentMecanographicNumbersAreNotTheSame() throws Exception {
        boolean expected = true;

        final String aMecanographicNumber = new String("abc");
        final String anotherMecanographicNumber = new String("qwe");

        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);

        final SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", this.anEmail,
                roles);

      
        final OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

        final CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
                aMecanographicNumber);

        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
                anotherMecanographicNumber);

        expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

        assertFalse(expected);
    }

    @Test
    public void ensureTwoCafeteriaUsersWithDifferentSystemUsersAreNotTheSame() throws Exception {
        boolean expected = false;

        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);

        final SystemUser aSystemUser = new SystemUser("userNameA", "passwordA", "firsNameA", "lastNameA", this.anEmail,
                roles);

        final SystemUser anotherSystemUser = new SystemUser("userNameB", "passwordB", "firsNameB", "lastNameB",
                this.anotherEmail, roles);

        final String aMecanographicNumber = new String("abc");


        final OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");

        final CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
                aMecanographicNumber);

        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(anotherSystemUser, anOrganicUnit,
                aMecanographicNumber);

        expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

        assertFalse(expected);
    }

//    @Test
//    public void ensureTwoCafeteriaUsersWithDifferentAccountsAreNotTheSame() throws Exception {
//        boolean expected = false;
//
//        final Set<RoleType> roles = new HashSet<RoleType>();
//        roles.add(RoleType.Admin);
//
//        final SystemUser aSystemUser = new SystemUser("userName", "password", "firsName", "lastName", this.anEmail,
//                roles);
//
//        final String aMecanographicNumber = new String("abc");
//
//        final String anAccount = new String("accountA");
//        final String anotherAccount = new String("accountB");
//        final OrganicUnit anOrganicUnit = new OrganicUnit("acronym", "name", "description");
//
//        final CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
//                aMecanographicNumber);
//
//        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
//                aMecanographicNumber);
//
//        expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);
//
//        assertFalse(expected);
//    }

    @Test
    public void ensureTwoCafeteriaUsersWithDifferentOrganicUnitsAreNotTheSame() throws Exception {
        boolean expected = false;

        final Set<RoleType> roles = new HashSet<RoleType>();
        roles.add(RoleType.Admin);

        final SystemUser aSystemUser = new SystemUser("userName", "password", "firsName", "lastName", this.anEmail,
                roles);

        final String aMecanographicNumber = new String("abc");

        final OrganicUnit anOrganicUnit = new OrganicUnit("acronymA", "nameA", "descriptionA");
        final OrganicUnit anotherOrganicUnit = new OrganicUnit("acronymB", "nameB", "descriptionB");

        final CafeteriaUser aCafeteriaUser = new CafeteriaUser(aSystemUser, anOrganicUnit,
                aMecanographicNumber);

        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUser(aSystemUser, anotherOrganicUnit,
                aMecanographicNumber);

        expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

        assertFalse(expected);
    }

}
