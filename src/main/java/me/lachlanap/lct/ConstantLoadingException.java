/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.lachlanap.lct;

/**
 *
 * @author lachlan
 */
public class ConstantLoadingException extends RuntimeException {

    public ConstantLoadingException(String msg) {
        super(msg);
    }

    public ConstantLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
