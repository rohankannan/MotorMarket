package com.cs180proj.app;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 * CS 18000 Group Project
 *
 * AncestorListenerAdapter abstract class added to
 * help determine when components are added and
 * removed in GUI system
 *
 * @author (Rohan Kannan, Alistair Joseph,
 * Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 */
public abstract class AncestorListenerAdapter implements AncestorListener {
    public void ancestorAdded(AncestorEvent event) {}
    public void ancestorRemoved(AncestorEvent event) {}
    public void ancestorMoved(AncestorEvent event) {}
}
