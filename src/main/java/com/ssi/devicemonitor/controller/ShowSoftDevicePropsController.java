package com.ssi.devicemonitor.controller;

import com.ssi.devicemonitor.entity.DeviceType;
import com.ssi.devicemonitor.entity.HardwareDevice;
import com.ssi.devicemonitor.entity.SoftwareDevice;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ShowSoftDevicePropsController {

    @FXML
    private Label manufacturerField;
    @FXML
    private Label nameField;
    @FXML
    private Label versionField;
    @FXML
    private Label dateField;
    @FXML
    private Label typeField;

    @FXML
    public void setDevice(SoftwareDevice device) {
        nameField.setText(device.getName());
        manufacturerField.setText(device.getManufacturer());
        versionField.setText(device.getVersion());
        dateField.setText(device.getInstallationDate().toString());
        typeField.setText(device.getDeviceType().getDisplayName());
    }

}
