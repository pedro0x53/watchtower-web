package com.pedro0x53.WatchTower.models.Checklist;

public enum CaseType {
    COMMON(0, "Common"),
    IOS(1, "iOS"),
    ANDROID(2, "Android");

    private final int rawValue;
    private final String name;

    CaseType(int rawValue, String name) {
        this.rawValue = rawValue;
        this.name = name;
    }

    public int getRawValue() {
        return rawValue;
    }

    public String getName() {
        return name;
    }

    // Helper method to convert raw integer value to enum
    public static CaseType fromRawValue(int rawValue) {
        for (CaseType c : CaseType.values()) {
            if (c.rawValue == rawValue) {
                return c;
            }
        }
        throw new IllegalArgumentException("Invalid raw value for Case enum: " + rawValue);
    }
}
