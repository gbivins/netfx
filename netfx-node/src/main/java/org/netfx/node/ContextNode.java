/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netfx.node;

import java.util.Collections;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author gbivins Node that allows injecting services into the lookup Based on
 * FAQ here: http://wiki.netbeans.org/DevFaqNodeInjectingLookupContents
 */
public class ContextNode<T> extends AbstractNode implements LookupListener {

    private InstanceContent context = null;
    private Class<T> contextBeanType;
    private T contextBean = null;
    private Lookup.Result<InstanceContent.Convertor> contextServices = null;

    public ContextNode(Class<T> type, T bean) {
        this(type, bean, new InstanceContent());
    }

    public ContextNode(Class<T> type, T bean, InstanceContent ic) {
        super(Children.LEAF, new AbstractLookup(ic));
        context = ic;
        contextBean = bean;
        context.add(contextBean);
        contextBeanType = type;
        setDisplayName(contextBean.toString());
        //Populate lookup based on declaratively registered factories
        String pathInSystemFS = getRegistrationPath("injectables");
        contextServices = Lookups.forPath(pathInSystemFS).lookupResult(InstanceContent.Convertor.class);
        for (InstanceContent.Convertor<T, ?> factory : contextServices.allInstances()) {
            context.add(contextBean, factory);
        }
        resultChanged(new LookupEvent(contextServices));
    }

    private String getRegistrationPath(String subfolder) {
        return contextBeanType.getName().replace('.', '/') + "/" + subfolder;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        context.set(Collections.emptyList(), null);
        context.add(contextBean);
        for (InstanceContent.Convertor<T, ?> factory : contextServices.allInstances()) {
            context.add(contextBean, factory);
        }
    }
}
