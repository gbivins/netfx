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

import javafx.event.Event;
import javafx.event.EventType;

import javafx.scene.Node;

public class InteractionEvent<N extends Node> extends Event
{
   public static final EventType<InteractionEvent> DRAG_INTERACTION             = new EventType("DragNode");
   public static final EventType<InteractionEvent> PRESSED_INTERACTION          = new EventType("PressedNode");
   public static final EventType<InteractionEvent> RELEASED_INTERACTION = new EventType("ReleasedNode");
   public static final EventType<InteractionEvent> HOVER_INTERACTION = new EventType("HoveredNode");
   private double                                  mouseDelta[]                 = { 0, 0, 0 };
   private double                                  initialInteractionPosition[] = { 0, 0, 0 };
   private N                                       interactionNode              = null;

   public InteractionEvent(N source, EventType type, double[] deltaMouse, double[] initialPosition)
   {
      //need to figure out what the target 
      super(source, source, type);

      mouseDelta[0] = deltaMouse[0];
      mouseDelta[1] = deltaMouse[1];
      //      mouseDelta[2] = deltaMouse[2];
      initialInteractionPosition[0] = initialPosition[0];
      initialInteractionPosition[1] = initialPosition[1];
      //      initialInteractionPosition[2] = initialPosition[2];
      //TODO: this is a little awkward
      interactionNode = source;
   }


   //TODO: awkwardness continued
   public N getInteractionNode()
   {
      return interactionNode;
   }


   public double[] getInitialInteractionPosition()
   {
      return initialInteractionPosition;
   }


   public double[] getMouseDelta()
   {
      return mouseDelta;
   }
}
