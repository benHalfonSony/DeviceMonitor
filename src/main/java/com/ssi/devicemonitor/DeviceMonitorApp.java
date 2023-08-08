package com.ssi.devicemonitor;

import com.ssi.devicemonitor.controller.DeviceMonitorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeviceMonitorApp extends Application {
    private DeviceMonitorController controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(DeviceMonitorApp.class.getResource("device_monitor.fxml"));
        this.controller = new DeviceMonitorController();
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Device Monitor");
        primaryStage.show();
    }

    public DeviceMonitorController getController() {
        return this.controller;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
