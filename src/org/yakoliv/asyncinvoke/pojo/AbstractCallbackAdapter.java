/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yakoliv.asyncinvoke.pojo;

import org.yakoliv.asyncinvoke.events.Callback;

/**
 * This is a small abstract class that implements the <code>Callback</code>
 * interface to reduce developer's work. The only method that he should write
 * is the <code>doInBackground()</code> method missing in this class.
 * @author desbocages
 * @param <T> the type of the result that will be returned by the background
 * task.
 */
public abstract class AbstractCallbackAdapter<T> implements Callback<T> {

    protected Throwable exception = null;
    protected T result = null;
    protected String methodName;

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public void setException(Throwable ex) {
        exception = ex;
    }

    /**
     * @return the result
     */
    public T getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(T result) {
        this.result = result;
    }
    
    @Override
    public final void setInvokedMethodName(String name){
        System.out.println("Method: "+name);
        methodName = name;
    }
    
    @Override
    public String getInvokedMethodName(){
        return methodName;
    }

}
