/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.data;

import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author gbivins
 */
public interface InjectableJavaFXUpdaterSerivce<T,U extends JavaFXDataUpdaterService> extends InstanceContent.Convertor<T, U>{

    
}
