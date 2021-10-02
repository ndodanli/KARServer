/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kar.karuinterface;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ahmet İlter ŞAHİN
 */
public class Components {

    public JLabel angelx;
    public JLabel angely;
    public JLabel flameL;
    public JLabel voltageAnalogL;
    public JLabel ntcAnalogL;
    public JLabel dhtHumL;
    public JLabel dhtTempL;
    public JLabel dhtError;
    public JLabel MQValue;
    public JLabel carWarning;

    Components(JLabel angelxValue, JLabel angelyValue, JLabel dhtHumLValue, JLabel dhtTemLValue, JLabel flameValue, JLabel MQValue, JLabel carValue, JLabel ntcValue, JLabel voltageValue, JLabel dhtErrorValue) {
        this.angelx = angelxValue;
        this.angely = angelyValue;
        this.flameL = flameValue;
        this.dhtHumL = dhtHumLValue;
        this.dhtTempL = dhtTemLValue;
        this.dhtError = dhtErrorValue;
        this.voltageAnalogL = voltageValue;
        this.ntcAnalogL = ntcValue;
        this.MQValue = MQValue;
        this.carWarning = carValue;
    }
}
