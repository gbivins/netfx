/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.data;

import javafx.beans.Observable;


/**
 *
 * @author gbivins
 */
public interface JavaFXDataUpdaterService<C extends Observable> {

    void updateData(int index,C dataToModify) throws RuntimeException;

}

