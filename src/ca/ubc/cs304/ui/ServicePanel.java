package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.Delegate;

import javax.swing.*;

public class ServicePanel {
    private JPanel servicePanel = new JPanel();
    private Delegate delegate;

    /**logistics functions*/
    public ServicePanel(Delegate delegate) {
        this.delegate = delegate;
    }
    public JPanel getServicePanel() {
        return this.servicePanel;
    }
}
