package com.pedro0x53.WatchTower.services;

import com.pedro0x53.WatchTower.exceptions.ProjectNotFoundException;
import com.pedro0x53.WatchTower.models.Project;
import com.pedro0x53.WatchTower.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements ProjectServiceTemplate {
    @Autowired
    ProjectRepository repository;
    @Override
    public Project save(Project project) {
        return repository.save(project);
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        projects.forEach(project -> {
            project.setup();
        });
        return projects;
    }

    @Override
    public Project findProjectByID(String id) {
        Project project = repository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        project.setup();
        return project;
    }

    @Override
    public Project update(String id, Project ephemeral) {
        Project persistent = repository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        persistent.setName(ephemeral.getName());
        persistent.setLevel(ephemeral.getLevel());
        persistent.setChecklist(ephemeral.getChecklist());
        return repository.save(persistent);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
