package com.pedro0x53.WatchTower.models;

import java.util.Arrays;

public enum VerificationLevel {
    L1(0, "L1"),
    L2(1, "L2"),
    L1R(2, "L1+R"),
    L2R(3, "L2+R");

    private final int rawValue;
    private final String description;

    VerificationLevel(int rawValue, String description) {
        this.rawValue = rawValue;
        this.description = description;
    }

    public int getRawValue() {
        return rawValue;
    }

    public String description() {
        return description;
    }

    public static VerificationLevel[] allCases() {
        return values();
    }

    public int getId() {
        return rawValue;
    }

    public static VerificationLevel fromRawValue(int rawValue) {
        return Arrays.stream(values())
                .filter(level -> level.rawValue == rawValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid raw value for VerificationLevel enum: " + rawValue));
    }
}
