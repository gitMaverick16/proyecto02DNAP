package com.example.proyecto02.MqttService;



public interface MqttHelperListener {
    void displayMessage(String data);
    void saveMessage(MqttMessageWrapper[] data, int size);
}
