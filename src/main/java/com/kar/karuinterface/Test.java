/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kar.karuinterface;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;

/**
 *
 * @author Ahmet İlter ŞAHİN
 */
public class Test implements Runnable {
    Webcam webcam;
    public Test(Webcam webcam){
        this.webcam = webcam;
    }

    @Override
    public void run() {
        while (true) {            
            BufferedImage bi = webcam.getImage();
        }
    }
    
}
