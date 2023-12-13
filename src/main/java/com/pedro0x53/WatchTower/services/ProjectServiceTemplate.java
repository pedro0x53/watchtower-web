package com.pedro0x53.WatchTower.services;

import com.pedro0x53.WatchTower.models.Project;

import java.util.List;

public interface ProjectServiceTemplate {
    Project save(Project project);

    List<Project> findAll();
    Project findProjectByID(String id);

    Project update(String id, Project newProject);

    void delete(String id);
}