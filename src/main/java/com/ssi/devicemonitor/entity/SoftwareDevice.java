package com.ssi.devicemonitor.entity;

import java.time.LocalDateTime;


public class SoftwareDevice extends Device {
    private LocalDateTime installationDate;

    public SoftwareDevice(String name, DeviceType deviceType, String version, String manufacturer) {
        super(name, deviceType, version, manufacturer);
        this.installationDate = LocalDateTime.now();
    }

    public LocalDateTime getInstallationDate() {
        return installationDate;
    }
}
