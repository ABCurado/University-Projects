/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csheets.persistence;

import csheets.ext.email.InformationEmailSent;
import csheets.framework.persistence.repositories.Repository;

/**
 *
 * @author Martins
 */
public interface EmailRepository extends Repository<InformationEmailSent, Long> {

}
