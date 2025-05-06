package com.cs180proj.app;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CS 18000 Group Project
 *
 * This class contains various methods (test cases) which test
 * the functionality of EditListingsPanel.
 *
 * @author (Rohan Kannan, Alistair Joseph, Lydia Schmucker, Stephen Tushentsov) lab sec 19
 *
 * @version May 3, 2025
 *
 */
public class EditListingsPanelTestCase {
    
    // Test case for EditListingsPanel class constructor and fields
    // Tests if clicking the "Back to Hub" button changes the current panel to "Hub"
    @Test
    public void testEditListingsOne() throws Exception {
        Server server = new Server();
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                try {
                    server.startServer(4242);
                } catch (Exception e) {
                }
            }
        });
        serverThread.start();
        Thread.sleep(150);
        MainFrame f = new MainFrame();
        f.setVisible(false);
        User user = new User("sc30", "3ball", 30000.3, "30 Oakland Dr");
        f.setCurrentUser(user);
        EditListingsPanel panel = new EditListingsPanel(f, f.getClient());
        JButton backButton = null;
        JScrollPane scrollPane = null;
        for (java.awt.Component c : panel.getComponents()) {
            if (c instanceof JButton button) {
                if ("Back to Hub".equals(button.getText())) {
                    backButton = button;
                }
            } else if (c instanceof JScrollPane) {
                scrollPane = (JScrollPane) c;
            } else if (c instanceof JPanel inner) {
                for (java.awt.Component c2 : inner.getComponents()) {
                    if (c2 instanceof JButton btn) {
                        if ("Back to Hub".equals(btn.getText())) {
                            backButton = btn;
                        }
                    }
                }
            }
        }
        assertNotNull(backButton);
        assertNotNull(scrollPane);
        backButton.doClick();
        Thread.sleep(100);
        assertEquals("Hub", f.getCurrentPanelName());
        f.dispose();
        server.stopServer();
        serverThread.join(500);
    }

    // Test case for EditListingsPanel class edit button functionality
    // Tests if clicking the "Edit" button opens the EditListingPanel dialog
    // When test is run, pop-up window appears, click cancel to close it
    @Test
    public void testEditListingsTwo() throws Exception {
        Server server = new Server();
        Thread serverThread = new Thread(() -> {
            try {
                server.startServer(4242);
            } catch (Exception e) {
            }
        });
        serverThread.start();
        Thread.sleep(150);
        MainFrame f = new MainFrame();
        f.setVisible(false);
        User user = new User("sc30", "3ball", 30000.3, "30 Oakland Dr");
        f.setCurrentUser(user);
        EditListingsPanel panel = new EditListingsPanel(f, f.getClient());
        JFrame tempFrame = new JFrame();
        tempFrame.add(panel);
        tempFrame.pack();
        tempFrame.setVisible(true);
        Thread.sleep(400);
        SwingUtilities.invokeAndWait(() -> {});
        JButton editButton = null;
        for (java.awt.Component c : panel.getComponents()) {
            if (c instanceof JScrollPane scrollPane) {
                JPanel listingsContainer = (JPanel) scrollPane.getViewport().getView();
                for (java.awt.Component c2 : listingsContainer.getComponents()) {
                    if (c2 instanceof JPanel cardPanel) {
                        for (java.awt.Component c3 : cardPanel.getComponents()) {
                            if (c3 instanceof JPanel rightPanel) {
                                for (java.awt.Component c4 : rightPanel.getComponents()) {
                                    if (c4 instanceof JButton button) {
                                        if (button.getText() != null && button.getText().trim().contains("Edit")) {
                                            editButton = button;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        assertNotNull(editButton);
        editButton.doClick();
        SwingUtilities.invokeAndWait(() -> {});
        // thread made to wait for closed dialog
        Thread waitForDialogClose = new Thread(() -> {
            try {
                Thread.sleep(300);
                for (java.awt.Window w : javax.swing.JDialog.getWindows()) {
                    if (w instanceof javax.swing.JDialog && w.isVisible()) {
                        w.dispose();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        waitForDialogClose.start();
        waitForDialogClose.join(850);
        boolean dialogFound = false;
        // when dialog is closed thread joins and checks if dialog is closed
        for (java.awt.Window w : javax.swing.JDialog.getWindows()) {
            if (w instanceof javax.swing.JDialog && w.isVisible()) {
                dialogFound = true;
            }
        }
        assertFalse(dialogFound);
        f.dispose();
        server.stopServer();
        serverThread.join(500);
    }
}
