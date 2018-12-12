/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yakoliv.asyncinvoke.events;

/**
 * This interface will be implemented by callback classes.
 *
 * @author YAKAM Olivier (olivier.yakam@yahoo.fr)
 */
public interface Callback<T> {

    /**
     * Used to make some validations or initializations before starting a task.
     */
    public void doBefore();

    /**
     * Used to handle the result of a background process. The implementing class
     * will provide the business logic. This method is called by the executor
     * object in the <code> AsyncProcessor </code> children.
     *
     * @see AsyncProcessor
     * @param result the result issued by the background process.
     */
    public void handleResult(T result);

    /**
     * If any exception is raised during the execution of the task, it is caught
     * and provided through this method. There is no need for the developer to
     * implement it. It is iplemented in the 
     *  <code> AbstractCallbackAdapter </code> class.
     *
     * @see AbstractCallbackAdapter
     * @return the raised exception.
     */
    public Throwable getException();

    /**
     * If any exception is raised during the execution of the task, it is caught
     * and kept in the  <code> AbstractCallbackAdapter </code> object using this
     * method. There is no need for the developer to implement it. It is
     * iplemented in the <code> AbstractCallbackAdapter </code> class.
     *
     * @see AbstractCallbackAdapter
     * @param ex the exception to set.
     */
    public void setException(Throwable ex);
    
    /**
     * Used to get the name of the procedure that has been invoked.
     * @return the name of the procedure.
     */
    public String getInvokedMethodName();
    
    /**
     *
     * @param name the name to set.
     */
    public void setInvokedMethodName(String name);
}
