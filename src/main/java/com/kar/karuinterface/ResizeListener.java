/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kar.karuinterface;

import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

/**
 *
 * @author Ahmet İlter ŞAHİN
 */
public class ResizeListener extends ComponentAdapter {

    WebcamPanel cameraPanel;

    public ResizeListener(WebcamPanel cameraPanel) {
        this.cameraPanel = cameraPanel;
    }
    

    @Override
    public void componentResized(ComponentEvent e) {
        Component panel = e.getComponent();
        Dimension jpanel2Size = panel.getSize();
        Dimension cameraPanelSize = cameraPanel.getSize();
        cameraPanel.setPreferredSize(panel.getSize());
        cameraPanel.revalidate();
        cameraPanel.repaint();
        panel.revalidate();
        panel.repaint();

    }

}
