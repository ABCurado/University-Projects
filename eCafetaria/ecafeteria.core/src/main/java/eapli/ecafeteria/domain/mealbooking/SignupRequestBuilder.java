package eapli.ecafeteria.domain.mealbooking;

import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.framework.domain.Factory;
import java.util.Calendar;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class SignupRequestBuilder implements Factory<SignupRequest> {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private OrganicUnit organicUnit;
    private String mecanographicNumber;
    private Calendar createdOn;

    public SignupRequestBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public SignupRequestBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public SignupRequestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public SignupRequestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public SignupRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public SignupRequestBuilder withOrganicUnit(OrganicUnit organicUnit) {
        this.organicUnit = organicUnit;
        return this;
    }

    public SignupRequestBuilder withMecanographicNumber(String mecanographicNumber) {
        this.mecanographicNumber = mecanographicNumber;
        return this;
    }

    public SignupRequestBuilder withCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public SignupRequest build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        if (this.createdOn != null) {
            return new SignupRequest(this.username, this.password, this.firstName, this.lastName, this.email, this.organicUnit, this.mecanographicNumber,
                    this.createdOn);
        } else {
            return new SignupRequest(this.username, this.password, this.firstName, this.lastName, this.email, this.organicUnit, this.mecanographicNumber);
        }
    }

}
