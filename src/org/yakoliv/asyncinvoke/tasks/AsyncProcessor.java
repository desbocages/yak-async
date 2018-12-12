/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yakoliv.asyncinvoke.tasks;

import java.lang.reflect.ParameterizedType;
import javax.swing.SwingWorker;
import org.yakoliv.asyncinvoke.events.Function;
import org.yakoliv.asyncinvoke.pojo.AbstractCallbackAdapter;
import org.yakoliv.asyncinvoke.pojo.OperationResult;

/**
 * This is one of the main classes the developers need to extend to provide the
 * business logic of the background task.
 *
 * @author YAKAM Olivier (olivier.yakam@yahoo.fr)
 * @param <V> type of the ouput of the process to execute in background
 * @param <T> type of the input of the process to execute in background e.g.
 *
 * <code>
 * public class MessageProcessor extends AsyncProcessor<String, Message> {
 *
 * @Override protected String doInBackground(Message msg) { return process(msg);
 * } private String process(Message m){ return "done."; } }
 * MessageProcessor<String, Message> processor = new MessageProcessor<>();
 * processor.doWork(new Message());
 * </code>
 */
public abstract class AsyncProcessor<V, T> {

    private final Executor executor;
    private AbstractCallbackAdapter<V> callback;

    public AsyncProcessor() {
        executor = new Executor();
    }

    /**
     * builds the background process executor worker. It delegates the job to
     * the executor.
     *
     * @param input the input awaited by the background process.
     * @return the built worker that will be executed.
     */
    private SwingWorker<OperationResult<V>, Void> buildProcessor(T input) {
        return getExecutor().executeAsync(new Function.Callable<V>() {
            @Override
            public V call() throws Exception {
                return doInBackground(input);
            }
        });
    }

    /**
     * The method that will be implemented to provide a task to execute.
     *
     * @param input the input awaited by the background process.
     * @return the result of the background process.
     */
    protected abstract V doInBackground(T input);

    /**
     * @return the callback
     */
    public AbstractCallbackAdapter<V> getCallback() {
        return callback;
    }

    public void setCallback(AbstractCallbackAdapter<V> c) {
        System.out.println("Setting callback...");
        callback = c;
        try {
            Class<T> c1 = (Class<T>) ((ParameterizedType)
                    AsyncProcessor.this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[1];
            Class<T> c0 = (Class<T>) ((ParameterizedType)
                    AsyncProcessor.this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
            if (c1 != null) {
                callback.setInvokedMethodName(c0.getSimpleName()+" "+this.getClass().getSimpleName()
                        + ".doInBackground("
                        + (c1.getSimpleName()) + " param);");
//                System.out.println(this.getClass().getSimpleName()
//                        + ".doInBackground("
//                        + (c1.getSimpleName()) + " param);");
            }

        } catch (Exception e) {
            callback.setInvokedMethodName(null);
        }

        getExecutor().setCallback(c);
    }

    /**
     * launches the background process. It is the main method that developers
     * will call to start background tasks
     *
     * @param input the input awaited by the background process.
     */
    public void doWork(T input) {
        SwingWorker<OperationResult<V>, Void> backgroundWorker = buildProcessor(input);
        if (backgroundWorker != null) {
            backgroundWorker.execute();
        }
    }

    /**
     * @return the executor
     */
    public Executor getExecutor() {
        return executor;
    }

}
