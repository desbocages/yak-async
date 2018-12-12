

package org.yakoliv.asyncinvoke.pojo;

/**
 * A class to encapsulate data around the background task execution.
 * It is only used by the library for inner purposes.
 * @author desbocages
 */
public class OperationResult<T> {
 private Throwable thrownException;
 private T gottenResult;
 private String invokedMethodName;

    /**
     * @return the thrownException
     */
    public Throwable getThrownException() {
        return thrownException;
    }

    /**
     * @param thrownException the thrownException to set
     */
    public void setThrownException(Throwable thrownException) {
        this.thrownException = thrownException;
    }

    /**
     * @return the gottenResult
     */
    public T getGottenResult() {
        return gottenResult;
    }

    /**
     * @param gottenResult the gottenResult to set
     */
    public void setGottenResult(T gottenResult) {
        this.gottenResult = gottenResult;
    }

    /**
     * @return the invokedMethodName
     */
    public String getInvokedMethodName() {
        return invokedMethodName;
    }

    /**
     * @param invokedMethodName the invokedMethodName to set
     */
    public void setInvokedMethodName(String invokedMethodName) {
        this.invokedMethodName = invokedMethodName;
    }
}
