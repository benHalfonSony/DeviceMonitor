package com.ssi.devicemonitor.entity;

public enum DeviceType {
    HARDWARE("Hardware Device"),
    SOFTWARE("Software Device");
    private final String displayName;

    DeviceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}