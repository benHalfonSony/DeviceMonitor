package com.ssi.devicemonitor.controller;

import com.ssi.devicemonitor.entity.Device;
import com.ssi.devicemonitor.entity.DeviceMonitor;
import com.ssi.devicemonitor.entity.GeneralDevice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.layout.VBox;

public class DeviceMonitorController {
    @FXML
    private ListView<Device> deviceListView;

    @FXML
    private TextField deviceNameTextField;

    @FXML
    private Button addDeviceButton;

    @FXML
    private ComboBox<String> deviceTypeComboBox;
    //VBox

    @FXML
    private TextField dialog;

    @FXML
    private TextField deviceProperties;

    private DeviceMonitor deviceMonitor;


    public void initialize() {
        deviceMonitor = new DeviceMonitor();

        deviceMonitor.addDevice(new GeneralDevice("Device 1"));
        deviceMonitor.addDevice(new GeneralDevice("Device 2"));
        deviceMonitor.addDevice(new GeneralDevice("Device 3"));

        deviceListView.setItems(FXCollections.observableList(deviceMonitor.getDevices()));
        deviceListView.setCellFactory(deviceListView -> new DeviceListCell());

        // Add context menu to ListView
        ContextMenu contextMenu = new ContextMenu();
        MenuItem removeItem = new MenuItem("Remove");
        MenuItem propertiesItem = new MenuItem("Properties");

        removeItem.setOnAction(event -> {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
            if (selectedDevice != null) {
                deviceMonitor.removeDevice(selectedDevice);
            }
        });

        propertiesItem.setOnAction(event -> {
            Device selectedDevice = deviceListView.getSelectionModel().getSelectedItem();
            if (selectedDevice != null) {
                deviceProperties.setText(selectedDevice.getName()+" , "+selectedDevice.getStatus()+" , "+selectedDevice.toString().substring(42));
                deviceMonitor.showDeviceProperties(selectedDevice);
            }
        });

        contextMenu.getItems().addAll(removeItem);
        contextMenu.getItems().addAll(propertiesItem);
        deviceListView.setContextMenu(contextMenu);

    }

    private class DataUpdateTask extends TimerTask {
        private Random random = new Random();

        @Override
        public void run() {
            refreshListView();
        }
    }

    @FXML
    private void addDevice() {
        String deviceName = deviceNameTextField.getText();
        // Create a ComboBox with device types options
        //ComboBox<String> deviceTypeComboBox;
        deviceTypeComboBox = new ComboBox<String>();
        deviceTypeComboBox.setPromptText("Select Device Type");
        deviceTypeComboBox.getItems().add("Hardware Device");
        deviceTypeComboBox.setValue("Hardware Device");
        deviceTypeComboBox.getItems().add("Software Device");

        // Create a layout for the ComboBox
        VBox layout = new VBox(10);
        layout.getChildren().add(deviceTypeComboBox);

        // Create an event handler for the ComboBox selection change
        deviceTypeComboBox.setOnAction(e -> {
            String selectedDeviceType = deviceTypeComboBox.toString();
            if (selectedDeviceType != null) {
                switch (selectedDeviceType) {
                    case "Hardware Device":
                        // Perform actions for hardware device
                        System.out.println("Selected Hardware Device.");
                        break;
                    case "Software Device":
                        // Perform actions for software device
                        System.out.println("Selected Software Device.");
                        break;
                    default:
                        // Handle default case if needed
                        break;
                }
            }
        });

        if (!deviceName.trim().equals("")) {
            Device newDevice = new GeneralDevice(deviceName);
            deviceMonitor.addDevice(newDevice);
            showUpdateDialog();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Device has no name, please enter a name for the device");
            ButtonType result = alert.showAndWait().orElse(ButtonType.CLOSE);
        }
        deviceNameTextField.clear();
    }

    public void refreshListView() {
        deviceListView.refresh();
        showUpdateDialog();
    }

    public void showUpdateDialog() {
        LocalDateTime lastUpdateTime=LocalDateTime.now();
        //Israel time
        ZoneOffset offset = ZoneOffset.ofHours(2);
        LocalDateTime nextUpdateTime=LocalDateTime.ofEpochSecond(5,5,offset);

        String lastUpdateTimeStr = lastUpdateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String nextUpdateTimeStr = nextUpdateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Time Information");
        alert.setHeaderText(null);
        alert.setContentText("Last Update Time: " + lastUpdateTimeStr + "\nNext Scheduled Update Time: " + nextUpdateTimeStr);
        System.out.println("Last Update Time: " + lastUpdateTimeStr + "\nNext Scheduled Update Time: " + nextUpdateTimeStr);
        String time = ("Last Update Time: " + lastUpdateTimeStr + "\nNext Scheduled Update Time: " + nextUpdateTimeStr);
        dialog.setEditable(true);
        dialog.setText(time);
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
}
