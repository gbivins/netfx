/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.node;

import java.util.Collection;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author gbivins
 */
public class ContextNodeChildFactory<T> extends ChildFactory<T> {

    private Collection<T> contextObjects = null;
    public ContextNodeChildFactory(Collection<T> objects) {
        contextObjects = objects;
    }

    
    @Override
    protected boolean createKeys(List<T> list) {
        list.addAll(contextObjects);
        return true;
    }

    @Override
    protected Node createNodeForKey(T key) {
        return new ContextNode(key.getClass(), key);
    }
}
