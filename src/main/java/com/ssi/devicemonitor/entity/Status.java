package com.ssi.devicemonitor.entity;

public enum Status {
    ONLINE("Online"),
    OFFLINE("Offline");
    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}