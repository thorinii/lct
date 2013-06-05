package me.lachlanap.lct;

import java.lang.annotation.*;

/**
 *
 * @author lachlan
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IntConstant {

    public String name();

    public int min() default Integer.MIN_VALUE;

    public int max() default Integer.MAX_VALUE;
}
