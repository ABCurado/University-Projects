package vendor.volt;

public class Action {

    /**
     * Executes a action.
     * 
     * <p>Sample declaration of a Action:</p>
     * 
     * <pre>
     * <code>
     * server.expect(":route", new Action() {
     *     &#64;Override
     *     public void run(Request request) {
     *         // ...
     *     }
     * });
     * </code>
     * </pre>
     * 
     * @param request Received request.
     **/
    public void run(Request request) {
        throw new UnsupportedOperationException("The Action run method must be defined in a separate class or anonymously.");
    }
    
}
