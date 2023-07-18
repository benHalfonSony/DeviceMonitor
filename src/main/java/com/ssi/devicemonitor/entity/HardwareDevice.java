package com.ssi.devicemonitor.entity;

public class HardwareDevice extends DeviceType {
    private String location;
    private String macAddress;

    // Constructor for HardwareDevice class
    public HardwareDevice(String manufacturer, String deviceType, String version, String location, String macAddress) {
        super(manufacturer, deviceType, version);
        this.location = location;
        this.macAddress = macAddress;
    }

    // Getters and setters for the fields specific to HardwareDevice
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
