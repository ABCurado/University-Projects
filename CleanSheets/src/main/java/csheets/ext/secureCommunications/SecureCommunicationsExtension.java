package csheets.ext.secureCommunications;

import csheets.ext.Extension;
import csheets.ext.secureCommunications.ui.UIExtensionSecureCommunications;
import csheets.ui.ctrl.UIController;
import csheets.ui.ext.UIExtension;

/**
 * Provides an extension to support monitoring of secure communications. An
 * extension must extend the Extension abstract class. The class that implements
 * the Extension is the "bootstrap" of the extension.
 *
 * @see Extension
 * @author Renato Machado
 */
public class SecureCommunicationsExtension extends Extension {

    /**
     * The name of extension
     */
    public static final String NAME = "Secure Communications";

    /**
     * Creates a new Share extension.
     */
    public SecureCommunicationsExtension() {
        super(NAME);
    }

    /**
     * Returns the user interface extension of this extension
     *
     * @param uiController the user interface controller
     * @return a user interface extension, or null if none is provided
     */
    public UIExtension getUIExtension(UIController uiController) {
        return new UIExtensionSecureCommunications(this, uiController);
    }
}
