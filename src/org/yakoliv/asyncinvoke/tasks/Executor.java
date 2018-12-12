/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yakoliv.asyncinvoke.tasks;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.yakoliv.asyncinvoke.events.Callback;
import org.yakoliv.asyncinvoke.events.Function;
import org.yakoliv.asyncinvoke.pojo.OperationResult;

/**
 * This class is the main class of this library. It is the heart of all 
 * processes. It is the one that makes the asynchronous task possible, creating
 * and providing the background worker to the application.
 * @author YAKAM Olivier (olivier.yakam@yahoo.fr)
 */
public class Executor implements IExecutor {

    private Callback callback = null;

    public Executor(Callback c) {
        callback = c;
    }

    public Executor() {
    }
/**
 * This method is used to dynamically generate a swing worker that will be used
 * to asynchronely perform the task.
 * @param <T> the type of the result awaited at the end of the process.
 * @param callable the interface which the operation will be called/executed.
 * @return An operation result object that will be used by the library to handle 
 * the result of the operation and pass it to the callback class for future use.
 */
    @Override
    public <T> SwingWorker<OperationResult<T>, Void> executeAsync(Function.Callable callable) {
        return new SwingWorker<OperationResult<T>, Void>() {

            @Override
            protected OperationResult<T> doInBackground() throws Exception {
                OperationResult<T> result = new OperationResult<>();
                try {
                    if (callback != null) {
                        result.setInvokedMethodName(callback.getInvokedMethodName());
                        callback.doBefore();
                    }
                    result.setGottenResult((T) callable.call());
                } catch (Throwable e) {
                    result.setThrownException(e);
                }
                return result;
            }

            @Override
            public void done() {
                try {
                    if (callback != null) {
                        OperationResult r = get();
                        if (r != null) {
                            if (r.getThrownException() != null) {
                                callback.setException(r.getThrownException());
                            }
                            callback.handleResult(r.getGottenResult());
                            callback.setInvokedMethodName(r.getInvokedMethodName());
                        }
                    }
                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
                    Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
    }

    /**
     * @return the callback
     */
    public Callback getCallback() {
        return callback;
    }

    /**
     * @param callback the callback to set
     */
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

}
