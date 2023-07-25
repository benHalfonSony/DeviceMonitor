package com.ssi.devicemonitor.entity;

import java.time.LocalDateTime;


public class HardwareDevice extends Device {
    private String macAddress;
    private String location;

    public HardwareDevice(String name, DeviceType deviceType, String version, String manufacturer, String macAddress, String location) {
        super(name, deviceType, version, manufacturer);
        this.macAddress = macAddress;
        this.location = location;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getLocation() {
        return location;
    }
}
