/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kar.karuinterface;

import java.awt.event.KeyEvent;

/**
 *
 * @author Ahmet İlter ŞAHİN
 */
public class KarKeyListener implements java.awt.event.KeyListener {

    Client client;

    public KarKeyListener(Client client) {
        this.client = client;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        client.sendData(String.valueOf(e.getKeyChar()));
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
