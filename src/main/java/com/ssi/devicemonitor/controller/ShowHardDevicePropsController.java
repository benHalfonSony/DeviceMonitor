package com.ssi.devicemonitor.controller;

import com.ssi.devicemonitor.entity.HardwareDevice;
import com.ssi.devicemonitor.entity.SoftwareDevice;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ShowHardDevicePropsController {
    @FXML
    private Label manufacturerField;
    @FXML
    private Label nameField;
    @FXML
    private Label versionField;
    @FXML
    private Label macAddressField;
    @FXML
    private Label locationField;

    @FXML
    private Label typeField;



    @FXML
    public void setDevice(HardwareDevice device) {
        nameField.setText(device.getName());
        manufacturerField.setText(device.getManufacturer());
        versionField.setText(device.getVersion());
        macAddressField.setText(device.getMacAddress());
        locationField.setText(device.getLocation());
        typeField.setText(device.getDeviceType().getDisplayName());
    }

}
