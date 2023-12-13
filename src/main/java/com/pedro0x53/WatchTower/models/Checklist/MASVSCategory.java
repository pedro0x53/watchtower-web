package com.pedro0x53.WatchTower.models.Checklist;

import java.util.Arrays;

public enum MASVSCategory {
    ARCH(1, "Arch"),
    STORAGE(2, "Storage"),
    CRYPTO(3, "Crypto"),
    AUTH(4, "Auth"),
    NETWORK(5, "Network"),
    PLATFORM(6, "Platform"),
    CODE(7, "Code"),
    RESILIENCE(8, "Resilience");

    private final int rawValue;
    private final String name;

    MASVSCategory(int rawValue, String name) {
        this.rawValue = rawValue;
        this.name = name;
    }

    public int getRawValue() {
        return rawValue;
    }

    public String getName() {
        return name;
    }

    public String getMSTG() {
        return "MSTG-" + name().toUpperCase();
    }

    public static MASVSCategory[] allCases() {
        return values();
    }

    public int getId() {
        return rawValue;
    }

    public static MASVSCategory fromRawValue(int rawValue) {
        for (MASVSCategory c : MASVSCategory.values()) {
            if (c.rawValue == rawValue) {
                return c;
            }
        }
        throw new IllegalArgumentException("Invalid raw value for MASVSCategory enum: " + rawValue);
    }
}
