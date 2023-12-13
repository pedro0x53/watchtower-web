package com.pedro0x53.WatchTower.controllers;

import com.pedro0x53.WatchTower.helpers.VerificationLevelCoder;
import com.pedro0x53.WatchTower.models.Project;
import com.pedro0x53.WatchTower.models.VerificationLevel;
import com.pedro0x53.WatchTower.repositories.ProjectRepository;
import com.pedro0x53.WatchTower.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class DashboardController {
    @Autowired
    ProjectService service;

    @Autowired
    VerificationLevelCoder coder;

    @GetMapping("/")
    public String dashboard(@RequestParam(required = false) String projectID, ModelMap model) {
        List<Project> projects = service.findAll();
        model.addAttribute("projects", projects);
        model.addAttribute("newProject", new Project());

        if (!projects.isEmpty()) {
            if (projectID == null) {
                model.addAttribute("openProject", projects.get(0));
            } else {
                Project selectedProject = projects.stream()
                        .filter(project -> Objects.equals(project.getId(), projectID))
                        .findFirst()
                        .orElse(null);
                model.addAttribute("openProject", selectedProject);
            }
        }

        return "dashboard";
    }

    @PostMapping("/newProject")
    public String newProject(Project newProject) {
        VerificationLevel level = VerificationLevel.fromRawValue(newProject.getRawLevel());
        newProject.setRawChecklist(coder.getRawChecklistForVerificationLevel(level));

        service.save(newProject);

        return "redirect:/";
    }
}