/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.authz;

import eapli.ecafeteria.application.AcceptRefuseSignupRequestController;
import eapli.ecafeteria.domain.mealbooking.SignupRequest;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;

/**
 *
 * Created by AJS on 08/04/2016.
 */
public class AcceptRefuseSignupRequestUI extends AbstractUI {

    private final AcceptRefuseSignupRequestController theController = new AcceptRefuseSignupRequestController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final SelectWidget<SignupRequest> selector = new SelectWidget<>(this.theController.listPendingSignupRequests(),
                new SignupRequestPrinter());
        selector.show();
        final SignupRequest theSignupRequest = selector.selectedElement();
        if (theSignupRequest != null) {
            System.out.println("1. Accept Signup Request");
            System.out.println("2. Refuse Signup Request");
            System.out.println("0. Exit");

            final int option = Console.readOption(1, 2, 0);
            // System.out.println("No valid option selected");

            if (option == 1) {
                try {
                    this.theController.acceptSignupRequest(theSignupRequest);
                } catch (final DataIntegrityViolationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (option == 2) {
                this.theController.refuseSignupRequest(theSignupRequest);
            } else {
                System.out.println("No valid option selected");
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Accept of Refuse Signup Requests";
    }
}
