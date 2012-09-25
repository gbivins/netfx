/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.data.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author gbivins
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface InjectableJavaFXService {
    public Class injectInto();
}
