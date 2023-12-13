package com.pedro0x53.WatchTower.models.Checklist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Requirement {
    private int id;
    @JsonIgnore
    private MASVSCategory category;
    private List<TestCase> testCases;
    private String description;
    private int status;

    public Requirement(int id, MASVSCategory category, List<TestCase> testCases, String description, int status) {
        this.id = id;
        this.category = category;
        this.testCases = testCases;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    @JsonIgnore
    public MASVSCategory getMASVSCategory() {
        return this.category;
    }
    public int getCategory() {
        return category.getRawValue();
    }
    public void setCategory(int rawCategory) {
        this.category = MASVSCategory.fromRawValue(rawCategory);
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    @JsonIgnore
    public String getMasvsID() {
        return category.getMSTG() + "-" + id;
    }
}
