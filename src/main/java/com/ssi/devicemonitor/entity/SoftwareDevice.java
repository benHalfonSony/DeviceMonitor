package com.ssi.devicemonitor.entity;
import java.time.LocalDateTime;

class SoftwareDevice extends DeviceType {
    private LocalDateTime installationDateTime;

    // Constructor for SoftwareDevice class
    public SoftwareDevice(String manufacturer, String deviceType, String version, LocalDateTime installationDateTime) {
        super(manufacturer, deviceType, version);
        this.installationDateTime = installationDateTime;
    }

    // Getter and setter for the field specific to SoftwareDevice
    public LocalDateTime getInstallationDateTime() {
        return installationDateTime;
    }

    public void setInstallationDateTime(LocalDateTime installationDateTime) {
        this.installationDateTime = installationDateTime;
    }
}
