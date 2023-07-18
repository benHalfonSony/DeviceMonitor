package com.ssi.devicemonitor.entity;
import java.time.LocalDateTime;


public abstract class DeviceType {
    private String manufacturer;
    private String deviceType;
    private String version;

    // Constructor for DeviceType class
    public DeviceType(String manufacturer, String deviceType, String version) {
        this.manufacturer = manufacturer;
        this.deviceType = deviceType;
        this.version = version;
    }

    // Getters and setters for the fields
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

