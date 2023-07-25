package com.ssi.devicemonitor.controller;

import com.ssi.devicemonitor.entity.DeviceType;
import com.ssi.devicemonitor.entity.HardwareDevice;
import com.ssi.devicemonitor.entity.SoftwareDevice;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HardwareDeviceFormController {
    private DeviceMonitorController mainController; //

    public void setMainController(DeviceMonitorController mainController) {
        this.mainController = mainController;
    }
    @FXML
    private TextField manufacturerField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField versionField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField macAddressField;

    @FXML
    private void submitForm() {
        String manufacturer = manufacturerField.getText();
        String name = nameField.getText();
        String version = versionField.getText();
        String location = locationField.getText();
        String macAddress = macAddressField.getText();

        // check if update or add
        //this.updateHardwareDevice.

        this.addHardwareDevice(name, manufacturer, version, location, macAddress);

        Stage stage = (Stage) manufacturerField.getScene().getWindow();
        // Close the stage
        stage.close();
    }

    private void addHardwareDevice(String name, String manufacturer, String version,
                                   String location, String macAddress){
        HardwareDevice newDevice = new HardwareDevice(name, DeviceType.HARDWARE,
                version, manufacturer, macAddress, location);
        this.mainController.addDevice(newDevice);
    }
}
