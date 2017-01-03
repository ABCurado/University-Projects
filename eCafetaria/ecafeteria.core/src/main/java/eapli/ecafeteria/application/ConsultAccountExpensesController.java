package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.mealbooking.CafeteriaUser;
import eapli.ecafeteria.domain.mealbooking.Transaction;
import eapli.ecafeteria.domain.mealbooking.TransactionType;
import java.util.ArrayList;

/**
 *
 * @author João Martins
 */
public class ConsultAccountExpensesController {
    
    /**
     * Return all transactions of debt type from all transaction.
     * @return list of debt transaction
     */
    public ArrayList<Transaction> allDebtTransactions(){
        ArrayList<Transaction> debtTransactions = new ArrayList<>();
        Iterable<CafeteriaUser> cafeteriaUser = new UserService().cafeteriaUserBySystemUser(AppSettings.getCurrentUser());
        
        if(!cafeteriaUser.iterator().hasNext()){
            return null;
        }
        
        CafeteriaUser user = cafeteriaUser.iterator().next();
        Iterable<Transaction> allTransactions = user.allTransactions();
        
        for (Transaction transaction : allTransactions) {
           if(transaction.getType() == TransactionType.DEBT){
               debtTransactions.add(transaction);
           } 
        }
        return debtTransactions;
    }
    
}
