/*
 * @(#) $Id$
 *
 * Copyright (c) 1997-2012 Object Reservoir, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Object Reservoir, Inc.
 * Use is subject to license terms.
 *
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netfx.node.interactor;

import javafx.event.EventType;
import javafx.scene.Node;

/**
 *
 * @author gbivins
 */
public interface Interactorable
{
   /**
    * Method addEventSeriesListener
    *
    *
    * @param l
    *
    */
   public void addInteractorHandler(InteractorHandler l);


   /**
    * Method removeEventSeriesListener
    *
    *
    * @param l
    *
    */
   public void removeInteractorHandler(InteractorHandler l);


   /**
    * Method fireSeriesChanged
    *
    *
    * @param e
    *
    */
   public void updateInteractionState(Node n, NodeInteractionState e, EventType type);
}
