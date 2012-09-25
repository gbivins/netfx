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

import java.util.EventListener;

import javafx.event.EventType;

import javafx.scene.Node;

/**
 *
 * @author gbivins
 */
public interface InteractorHandler<N extends Node> extends EventListener
{
   void interact(N node, EventType<InteractionEvent> type, NodeInteractionState state);
}
