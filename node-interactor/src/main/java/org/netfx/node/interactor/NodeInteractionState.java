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

/**
 *
 * @author gbivins
 */
public class NodeInteractionState
{
   private double[] mouseDelta            = { 0, 0 };
   private double[] nodePosition          = { 0, 0 };
   private double[] mouseAnchor           = { 0, 0 };
   private double[] mousePosition         = { 0, 0 };
   private double[] mouseDragLastPosition = { 0, 0 };

   /**
    * Get the value of mouseDragLastPosition
    *
    * @return the value of mouseDragLastPosition
    */
   public double[] getMouseDragLastPosition()
   {
      return mouseDragLastPosition;
   }


   /**
    * Set the value of mouseDragLastPosition
    *
    * @param mouseDragLastPosition new value of mouseDragLastPosition
    */
   public void setMouseDragLastPosition(double[] mouseDragLastPosition)
   {
      this.mouseDragLastPosition = mouseDragLastPosition;
   }


   /**
    * Get the value of mouseDragLastPosition at specified index
    *
    * @param index
    * @return the value of mouseDragLastPosition at specified index
    */
   public double getMouseDragLastPosition(int index)
   {
      return this.mouseDragLastPosition[index];
   }


   /**
    * Set the value of mouseDragLastPosition at specified index.
    *
    * @param index
    * @param newMouseDragDelta new value of mouseDragLastPosition at specified index
    */
   public void setMouseDragLastPosition(int index, double newMouseDragDelta)
   {
      this.mouseDragLastPosition[index] = newMouseDragDelta;
   }


   /**
    * Get the value of mousePosition
    *
    * @return the value of mousePosition
    */
   public double[] getMousePosition()
   {
      return mousePosition;
   }


   /**
    * Set the value of mousePosition
    *
    * @param mousePosition new value of mousePosition
    */
   public void setMousePosition(double[] mousePosition)
   {
      this.mousePosition = mousePosition;
   }


   /**
    * Get the value of mousePosition at specified index
    *
    * @param index
    * @return the value of mousePosition at specified index
    */
   public double getMousePosition(int index)
   {
      return this.mousePosition[index];
   }


   /**
    * Set the value of mousePosition at specified index.
    *
    * @param index
    * @param newMousePosition new value of mousePosition at specified index
    */
   public void setMousePosition(int index, double newMousePosition)
   {
      this.mousePosition[index] = newMousePosition;
   }


   /**
    * Get the value of mouseAnchor
    *
    * @return the value of mouseAnchor
    */
   public double[] getMouseAnchor()
   {
      return mouseAnchor;
   }


   /**
    * Set the value of mouseAnchor
    *
    * @param mouseAnchor new value of mouseAnchor
    */
   public void setMouseAnchor(double[] mouseAnchor)
   {
      this.mouseAnchor = mouseAnchor;
   }


   /**
    * Get the value of mouseAnchor at specified index
    *
    * @param index
    * @return the value of mouseAnchor at specified index
    */
   public double getMouseAnchor(int index)
   {
      return this.mouseAnchor[index];
   }


   /**
    * Set the value of mouseAnchor at specified index.
    *
    * @param index
    * @param newMouseAnchor new value of mouseAnchor at specified index
    */
   public void setMouseAnchor(int index, double newMouseAnchor)
   {
      this.mouseAnchor[index] = newMouseAnchor;
   }


   /**
    * Get the value of nodePosition
    *
    * @return the value of nodePosition
    */
   public double[] getNodePosition()
   {
      return nodePosition;
   }


   /**
    * Set the value of nodePosition
    *
    * @param nodePosition new value of nodePosition
    */
   public void setNodePosition(double[] initialPosition)
   {
      this.nodePosition = initialPosition;
   }


   /**
    * Get the value of nodePosition at specified index
    *
    * @param index
    * @return the value of nodePosition at specified index
    */
   public double getNodePosition(int index)
   {
      return this.nodePosition[index];
   }


   /**
    * Set the value of nodePosition at specified index.
    *
    * @param index
    * @param newInitialPosition new value of nodePosition at specified index
    */
   public void setNodePosition(int index, double newInitialPosition)
   {
      this.nodePosition[index] = newInitialPosition;
   }


   /**
    * Get the value of mouseDelta
    *
    * @return the value of mouseDelta
    */
   public double[] getMouseDelta()
   {
      return mouseDelta;
   }


   /**
    * Set the value of mouseDelta
    *
    * @param mouseDelta new value of mouseDelta
    */
   public void setMouseDelta(double[] mouseDelta)
   {
      this.mouseDelta = mouseDelta;
   }


   /**
    * Get the value of mouseDelta at specified index
    *
    * @param index
    * @return the value of mouseDelta at specified index
    */
   public double getMouseDelta(int index)
   {
      return this.mouseDelta[index];
   }


   /**
    * Set the value of mouseDelta at specified index.
    *
    * @param index
    * @param newMouseDelta new value of mouseDelta at specified index
    */
   public void setMouseDelta(int index, double newMouseDelta)
   {
      this.mouseDelta[index] = newMouseDelta;
   }
}
