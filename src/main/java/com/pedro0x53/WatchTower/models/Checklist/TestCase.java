package com.pedro0x53.WatchTower.models.Checklist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.net.URL;
import java.util.Optional;

public class TestCase {
    private int id;
    private CaseType type;
    private String url;

    public TestCase(int id, CaseType type, String url) {
        this.id = id;
        this.type = type;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public CaseType getCaseType() {
        return this.type;
    }
    public int getType() {
        return type.getRawValue();
    }

    public void setType(CaseType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) { this.url = url; }
}