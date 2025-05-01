package com.cs180proj.app;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public abstract class AncestorListenerAdapter implements AncestorListener {
    public void ancestorAdded(AncestorEvent event) {}
    public void ancestorRemoved(AncestorEvent event) {}
    public void ancestorMoved(AncestorEvent event) {}
}
