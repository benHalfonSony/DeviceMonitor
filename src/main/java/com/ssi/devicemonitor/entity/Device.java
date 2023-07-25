package com.ssi.devicemonitor.entity;

public abstract class Device {
    private String name;
    private Status status;
    private DeviceType deviceType;
    private String Version;
    private String Manufacturer;

    public Device(String name, DeviceType deviceType, String version, String manufacturer) {
        this.name = name;
        this.status = Status.OFFLINE;
        this.deviceType = deviceType;
        Version = version;
        Manufacturer = manufacturer;
    }


    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
