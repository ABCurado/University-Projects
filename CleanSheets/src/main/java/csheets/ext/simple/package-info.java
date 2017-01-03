/**
 * Provides an example of how to build a simple extension.
 * An extension is a class that extends the class Extension.
 * CleanSheets dynamically loads all the extensions that it finds declared in the following property files: res/extensions.props and extensions.props.
 * For the simple extension to be loaded at startup a line with the fully qualified name of the simple extension class must be present in one of the property files.
 * The fully qualified name of the simple extension is: csheets.ext.simple.ExtensionExample
 * 
 * <p>
 * <b>Class Diagram</b>
 * <p>
 * <img src="doc-files/simple_extension_image1.png" alt="image">
 * <p>
 * <b>Sequence Diagrams</b> illustrating the setup of the extension
 * <p>
 * The following sequence diagram illustrates the creation of the simple extension. 
 * All the extensions are loaded dynamically by the ExtensionManager at application startup.  
 * <img src="doc-files/simple_extension_image2.png" alt="image">
 *
 * <p>
 * The following sequence diagram illustrates the creation of the user interface extension. 
 * All the UI extensions are loaded by the UIController at application startup.  
 * <img src="doc-files/simple_extension_image3.png" alt="image">
 * 
 * <p>
 * The following sequence diagram illustrates the creation of the menu extension. 
 * All the menu extensions are loaded by the MenuBar at application startup.  
 * <img src="doc-files/simple_extension_image4.png" alt="image">
 *
 * <p>
 * <b>Sequence Diagrams</b> illustrating use cases of the extension
 * <p>
 * The following sequence diagram illustrates the use case <b>"Set the contents of the A1 cell"</b>. 
 * To be noticed that the operation actionPerformed does not originate directly from the User. There are several other classes involved that are not depicted for clarity purposes. 
 * 
 * <img src="doc-files/simple_extension_image5.png" alt="image">
 * 
 * @author Alexandre Braganca
 * 
 */

package csheets.ext.simple;


