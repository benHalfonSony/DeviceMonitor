package com.ssi.devicemonitor.entity;

import javafx.scene.control.Alert;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;


public class DeviceMonitor {
    private List<Device> devices;
    private Timer statusUpdateTimer;

    public DeviceMonitor() {
        devices = new ArrayList<>();

        // Start the timer to simulate status updates every few seconds
        statusUpdateTimer = new Timer();
        statusUpdateTimer.schedule(new StatusUpdateTask(), 0, 5000); // Update every 5 seconds
    }


    public List<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        this.devices.add(device);

        // Specify the file path
        String filePath = "c:\\Modan\\devices.txt";

        // Use try-with-resources to automatically close the writer
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Write the record to the file and add a newline character
            writer.write(device.getName());
            writer.newLine();

            System.out.println("Device added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the Device: " + e.getMessage());
        }
    }

    public void removeDevice(Device device) {
        this.devices.remove(device);

        // Specify the file path
        String filePath = "c:\\Modan\\devices.txt";

        try {
            // Create a temporary file to store the updated data
            File tempFile = new File("tempRecords.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            // Read the original file and copy all records except the one to remove
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.equals(device.getName())) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            // Close readers and writer
            reader.close();
            writer.close();

            // Delete the original file
            File originalFile = new File(filePath);
            if (originalFile.delete()) {
                // Rename the temporary file to the original file name
                tempFile.renameTo(originalFile);
                System.out.println("Device removed successfully.");
            } else {
                System.out.println("Failed to remove the Device.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while removing the record: " + e.getMessage());
        }
    }

    public void showDeviceProperties(Device device) {
        System.out.println(device.getName());
        System.out.println(device.getStatus());
        System.out.println(device.toString());
    }

    private class StatusUpdateTask extends TimerTask {
        private Random random = new Random();

        @Override
        public void run() {
            for (Device device : devices) {
                // Simulate random status updates
                boolean isOnline = random.nextBoolean();
                device.setStatus(isOnline ? "Online" : "Offline");
            }
        }
    }
}
