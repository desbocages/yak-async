package org.yakoliv.asyncinvoke.events;

/**
 *
 * @author YAKAM Olivier (olivier.yakam@yahoo.fr)
 */
public class Function {

    /**
     * Will be used by the  <code> Executor </code> to call procedures.
     *
     * @param <ToReturn> the parameterized type of data that will be returned by
     * the procedure.
     * @see Executor
     */
    public interface Callable<ToReturn> {

        ToReturn call() throws Exception;
    }
}
