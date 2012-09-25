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

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javax.swing.event.EventListenerList;

//TODO: maybe should be "Interactor"...Gestures from touch devices really
//should only differ by the EventType being handled...
//TODO: This should be able to handl 3D as well but there is no common "Point" class
//in javafx... ;(
public class NodeInteractor implements Interactorable
{
   private NodeDragEventHandler    dragEventHandler    = null;
   private NodePressEventHandler   pressEventHandler   = null;
   private NodeReleaseEventHandler releaseEventHandler = null;
   private Node                    interactiveNode     = null;
   private Point2D                 mouseAnchor         = null;
   private double                  nodePosition[]      =
   {
      0,
      0   /*,0*/
   };
   private double                  mouseDelta[]        = { 0, 0 };
   private NodeInteractionState    state               = null;

   public NodeInteractor(Node node)
   {
      interactiveNode     = node;
      nodePosition[0]     = interactiveNode.getTranslateX();
      nodePosition[1]     = interactiveNode.getTranslateY();
      state               = new NodeInteractionState();
      dragEventHandler    = new NodeDragEventHandler();
      pressEventHandler   = new NodePressEventHandler();
      releaseEventHandler = new NodeReleaseEventHandler();

      interactiveNode.setOnMouseDragged(dragEventHandler);
      interactiveNode.setOnMousePressed(pressEventHandler);
      interactiveNode.setOnMouseReleased(releaseEventHandler);
   }


   void updateMouseDelta(MouseEvent me)
   {
      //test handling here
      mouseDelta[0] = me.getSceneX() - mouseAnchor.getX();
      mouseDelta[1] = me.getSceneY() - mouseAnchor.getY();
   }


   protected EventListenerList listenerList = new EventListenerList();

   @Override
   public void addInteractorHandler(InteractorHandler l)
   {
      listenerList.add(InteractorHandler.class, l);
   }


   @Override
   public void removeInteractorHandler(InteractorHandler l)
   {
      listenerList.remove(InteractorHandler.class, l);
   }


   @Override
   public void updateInteractionState(Node n, NodeInteractionState e, EventType type)
   {
      Object[] listeners = listenerList.getListenerList();

      for(int i = 0; i < listeners.length; i += 2)
      {
         if(listeners[i] == InteractorHandler.class)
         {
            ((InteractorHandler)listeners[i + 1]).interact(n, type, e);
         }
      }
   }


   public void fireInteractionStateChange(Node n, NodeInteractionState state, EventType type)
   {
      updateInteractionState(n, state, type);
   }


   class NodeDragEventHandler implements EventHandler<MouseEvent>
   {
      @Override
      public void handle(MouseEvent me)
      {
         updateMouseDelta(me);
         //         NodeInteractionState state = new NodeInteractionState();
         state.setNodePosition(nodePosition);
         state.setMouseDelta(mouseDelta);

         double[] anchor = new double[2];

         anchor[0] = mouseAnchor.getX();
         anchor[1] = mouseAnchor.getY();

         state.setMouseAnchor(anchor);
         state.setMousePosition(0, me.getX());
         state.setMousePosition(1, me.getY());
         fireInteractionStateChange(interactiveNode, state, InteractionEvent.DRAG_INTERACTION);
         state.setMouseDragLastPosition(0, me.getX());
         state.setMouseDragLastPosition(1, me.getY());
      }
   }


   class NodePressEventHandler implements EventHandler<MouseEvent>
   {
      @Override
      public void handle(MouseEvent me)
      {
         mouseAnchor = new Point2D(me.getX(), me.getY());

         updateMouseDelta(me);

         nodePosition[0] = interactiveNode.getTranslateX();
         nodePosition[1] = interactiveNode.getTranslateY();

         state.setNodePosition(nodePosition);
         state.setMouseDelta(mouseDelta);

         double[] anchor = new double[2];

         anchor[0] = mouseAnchor.getX();
         anchor[1] = mouseAnchor.getY();

         state.setMouseAnchor(anchor);
         state.setMousePosition(0, me.getX());
         state.setMousePosition(1, me.getY());
         state.setMouseDragLastPosition(0, me.getX());
         state.setMouseDragLastPosition(1, me.getY());
         fireInteractionStateChange(interactiveNode, state, InteractionEvent.PRESSED_INTERACTION);
      }
   }


   class NodeReleaseEventHandler implements EventHandler<MouseEvent>
   {
      @Override
      public void handle(MouseEvent me)
      {
         mouseAnchor = new Point2D(me.getSceneX(), me.getSceneY());

         updateMouseDelta(me);
         //         NodeInteractionState state = new NodeInteractionState();
         state.setNodePosition(nodePosition);
         state.setMouseDelta(mouseDelta);

         double[] anchor = new double[2];

         anchor[0] = mouseAnchor.getX();
         anchor[1] = mouseAnchor.getY();

         state.setMouseAnchor(anchor);
         state.setMousePosition(0, me.getX());
         state.setMousePosition(1, me.getY());
         state.setMouseDragLastPosition(0, me.getX());
         state.setMouseDragLastPosition(1, me.getY());
         fireInteractionStateChange(interactiveNode, state, InteractionEvent.RELEASED_INTERACTION);
      }
   }
}
