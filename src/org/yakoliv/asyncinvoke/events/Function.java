package org.yakoliv.asyncinvoke.events;

/**
 *
 * @author desbocages
 */
public class Function {

    /**
     * Will be used by the  <code> Executor </code> to call procedures.
     *
     * @param <ToReturn> the parameterized type of data that will be returned by
     * the procedure.
     * @see org.yakoliv.asyncinvoke.tasks.Executor
     */
    public interface Callable<ToReturn> {

        ToReturn call() throws Exception;
    }
}
