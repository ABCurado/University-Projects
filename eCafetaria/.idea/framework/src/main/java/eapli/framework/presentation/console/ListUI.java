package eapli.framework.presentation.console;

import eapli.framework.visitor.Visitor;

/**
 * a generic List UI to avoid code duplication since most of the List UIs are
 * quite similar. while AbstractListUI allows the client code to create its own
 * derived classes, ListUI allows the client code to work only with objects,
 * instances, of this class without the need to define new classes.
 *
 * Created by Paulo Gandra Sousa
 */
public class ListUI<T> extends AbstractListUI<T> {

    protected final Iterable<T> source;
    protected final Visitor<T> printer;
    protected final String elementName;

    public ListUI(Iterable<T> source, Visitor<T> printer, String elementName) {
        this.source = source;
        this.printer = printer;
        this.elementName = elementName;
    }

    /**
     * the source to list
     *
     * @return
     */
    @Override
    protected Iterable<T> listOfElements() {
        return this.source;
    }

    /**
     * the visitor that prints the content of each element
     *
     * @return
     */
    @Override
    protected Visitor<T> elementPrinter() {
        return this.printer;
    }

    /**
     * the name of the type of elements to list
     *
     * @return
     */
    @Override
    protected String elementName() {
        return this.elementName;
    }
}
