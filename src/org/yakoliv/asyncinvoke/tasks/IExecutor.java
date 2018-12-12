/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yakoliv.asyncinvoke.tasks;

import javax.swing.SwingWorker;
import org.yakoliv.asyncinvoke.events.Function;
import org.yakoliv.asyncinvoke.pojo.OperationResult;

/**
 *
 * @author desbocages
 */
public interface IExecutor {
public <T> SwingWorker<OperationResult<T>,Void> executeAsync(final Function.Callable callable);
}
