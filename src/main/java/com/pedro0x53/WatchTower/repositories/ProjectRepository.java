package com.pedro0x53.WatchTower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedro0x53.WatchTower.models.Project;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, String> { }