package com.kar.karuinterface;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private BufferedReader in;
    private DataOutputStream out;     // object to write data into socket
    static private String msg;
    Components components;

    Client(Components components) {
        this.components = components;
        clientSocket = null;
    }

    public void run() {
        try {
            String host = "192.168.137.1",
                    hostLocal = "localhost";
            clientSocket = new Socket(host, 5000);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread receiver = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while (msg != null) {
                            changeComponents(msg);
                            System.out.println(msg);
                            msg = in.readLine();
                        }
                        System.out.println("Server out of service");
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                private void changeComponents(String msg) {
                    if (msg.length() >= 2 && isNumeric(msg.substring(0, 2).trim())) {
                        int id = Integer.parseInt(msg.substring(0, 2).trim());
                        msg = msg.substring(2).trim();
                        switch (id) {

                            case ID.ANGLE_X:
                                components.angelx.setText(msg);
                                break;

                            case ID.ANGLE_Y:
                                components.angely.setText(msg);
                                break;

                            case ID.FLAME_DIGITAL:
                                components.flameL.setText(msg);
                                break;

                            //case ID.FLAME_ANALOG:
                            //   components.flameanalogL.setText(msg);
                            // break;
                            case ID.VOLTAJ_ANALOG_INT:
                                convertToPercentage(msg);
                                break;

                            case ID.NTC_ANALOG_INT:
                                components.ntcAnalogL.setText(msg);
                                break;

                            case ID.DHT_DIGITAL_HUM:
                                components.dhtHumL.setText(msg);
                                break;

                            case ID.DHT_DIGITAL_TEMP:
                                components.dhtTempL.setText(msg);
                                break;

                            case ID.DHT_ERROR:
                                components.dhtError.setText(msg);
                                break;

                            case ID.MG_DIGITAL_INT:
                                components.MQValue.setText(msg);
                                break;
                            case ID.MG_ANALOG_VAR:
                                components.MQValue.setText(msg);
                                break;
                            case ID.CAR_WARNING:
                                components.carWarning.setText(msg);
                                break;

                            default:
                                break;
                        }
                    }
                }

                private boolean isNumeric(String strNum) {
                    try {
                        double d = Double.parseDouble(strNum);
                    } catch (NumberFormatException nfe) {
                        return false;
                    }
                    return true;
                }

                private void convertToPercentage(String msg) {
                    int valueInt;
                    float value = Float.parseFloat(msg);
                    if (value <= 11.0f) {
                        valueInt = 0;
                    } else {
                        value = (value - 11.0f) * 100;
                        valueInt = (int) value;
                    }
                    components.voltageAnalogL.setText(String.valueOf(valueInt) + "%");
                }
            });
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(String data) {
        msg = data.toUpperCase();
        try {
            out.writeBytes(msg + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
