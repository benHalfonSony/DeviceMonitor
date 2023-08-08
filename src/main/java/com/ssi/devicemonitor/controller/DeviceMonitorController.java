package com.ssi.devicemonitor.controller;

import com.ssi.devicemonitor.DeviceMonitorApp;
import com.ssi.devicemonitor.entity.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;

public class DeviceMonitorController {
    @FXML
    private ListView<Device> deviceListView;

    @FXML
    private Button addSoftwareDeviceButton;
    @FXML
    private Button addHardwareDeviceButton;

    private DeviceMonitor deviceMonitor;


    public void initialize() {
        deviceMonitor = new DeviceMonitor();

//        deviceMonitor.addDevice(new GeneralDevice("Device 1"));
//        deviceMonitor.addDevice(new GeneralDevice("Device 2"));
//        deviceMonitor.addDevice(new GeneralDevice("Device 3"));

        deviceListView.setItems(FXCollections.observableList(deviceMonitor.getDevices()));
        deviceListView.setCellFactory(deviceListView -> new DeviceListCell());

        // Add context menu to ListView
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeItem = new MenuItem("Remove");

        removeItem.setOnAction(event -> {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
            if (selectedDevice != null) {
                deviceMonitor.removeDevice(selectedDevice);
                refreshListView();
            }
        });

        contextMenu.getItems().addAll(removeItem);
        deviceListView.setContextMenu(contextMenu);

    }

    private class DataUpdateTask extends TimerTask {
        private Random random = new Random();

        @Override
        public void run() {
            refreshListView();
        }
    }


    public void addDevice(Device newDevice) {
        deviceMonitor.addDevice(newDevice);
        deviceListView.setItems(FXCollections.observableList(deviceMonitor.getDevices())); // Set items again
        deviceListView.setCellFactory(deviceListView -> new DeviceListCell()); // Set the cellFactory again
        refreshListView();
    }

    public void refreshListView() {
        deviceListView.refresh();
    }

    public DeviceMonitor getDeviceMonitor() {
        return deviceMonitor;
    }

    private class DeviceListCell extends ListCell<Device> {
        @Override
        protected void updateItem(Device device, boolean empty) {
            super.updateItem(device, empty);

            if (device == null || empty) {
                setText(null);
                setGraphic(null);
                setStyle(""); // Reset the cell style
            } else {
                setText(device.getName() + " - " + device.getStatus());

                // Set the cell style based on the device status
                if (device.getStatus().equals("Online")) {
                    setStyle("-fx-text-fill: green;");
                } else if (device.getStatus().equals("Offline")) {
                    setStyle("-fx-text-fill: red;");
                } else {
                    setStyle(""); // Reset the cell style
                }
            }
        }
    }
    @FXML
    private void openSoftwareAddingFormButtonClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DeviceMonitorApp.class.getResource("software_device_form.fxml"));
            Parent root = fxmlLoader.load();
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(new Scene(root));
            secondaryStage.setTitle("Adding software device Form");
            SoftwareDeviceFormController softwareDeviceFormController  = fxmlLoader.getController();
            softwareDeviceFormController.setMainController(this);
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openHardwareAddingFormButtonClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DeviceMonitorApp.class.getResource("hardware_device_form.fxml"));
            Parent root = fxmlLoader.load();
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(new Scene(root));
            secondaryStage.setTitle("Adding hardware device Form");
            HardwareDeviceFormController hardwareDeviceFormController  = fxmlLoader.getController();
            hardwareDeviceFormController.setMainController(this);
            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showDevicePropsButtonClicked() {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
            DeviceType type = this.deviceMonitor.getDeviceByName(selectedDevice.getName()).getDeviceType();
            if(type.equals(DeviceType.HARDWARE)){
                this.showHardDeviceDetailsDialog();
            } else {
               this.showSoftDeviceDetailsDialog();
            }
    }

    private void showSoftDeviceDetailsDialog() {
        Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
        if (selectedDevice != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DeviceMonitorApp.class.getResource("soft_device_details_dialog.fxml"));
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.getDialogPane().setContent(fxmlLoader.load());

                ShowSoftDevicePropsController dialogController = fxmlLoader.getController();
                dialogController.setDevice((SoftwareDevice) selectedDevice);

                dialog.setTitle("Device Details");
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                dialog.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showHardDeviceDetailsDialog() {
        Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
        if (selectedDevice != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DeviceMonitorApp.class.getResource("hard_device_details_dialog.fxml"));
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.getDialogPane().setContent(fxmlLoader.load());

                ShowHardDevicePropsController dialogController = fxmlLoader.getController();
                dialogController.setDevice((HardwareDevice) selectedDevice);

                dialog.setTitle("Device Details");
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                dialog.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
