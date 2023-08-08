package com.ssi.devicemonitor.controller;

import com.ssi.devicemonitor.entity.DeviceType;
import com.ssi.devicemonitor.entity.SoftwareDevice;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SoftwareDeviceFormController {
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
    private void submitForm() {
        String manufacturer = manufacturerField.getText();
        String name = nameField.getText();
        String version = versionField.getText();

        SoftwareDevice device = (SoftwareDevice)this.mainController.getDeviceMonitor().getDeviceByName(name);

        // check if update or add
        if(device != null){

//          updateSoftwareDevice(device);
        } else {
            this.addSoftwareDevice(name, manufacturer, version);
        }
        Stage stage = (Stage) manufacturerField.getScene().getWindow();
        // Close the stage
        stage.close();
    }

    private void addSoftwareDevice(String name, String manufacturer, String version){
        SoftwareDevice newDevice = new SoftwareDevice(name, DeviceType.SOFTWARE, version, manufacturer);
        this.mainController.addDevice(newDevice);
    }
//
//    private void updateSoftwareDevice(SoftwareDevice device){
//        device.setManufacturer();
//    }
}
