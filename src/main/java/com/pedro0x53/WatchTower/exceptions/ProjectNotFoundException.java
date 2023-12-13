package com.pedro0x53.WatchTower.exceptions;

public class ProjectNotFoundException extends  RuntimeException {
    public ProjectNotFoundException (String id) {
        super("Unable to find project by id: " + id);
    }
}
