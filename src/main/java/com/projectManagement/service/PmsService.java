package com.projectManagement.service;

import com.projectManagement.model.Project;
import com.projectManagement.model.Resource;
import com.projectManagement.model.Task;

import java.util.List;
import java.util.Optional;

public interface PmsService {

    Project createProject(Project project);
    List<Project> getAllProjects();
    Project getProjectById(Long projectId);
    String deleteProject(Long projectId);

    Task createTask(Task task, Long projectId);
    List<Task> getAllTasks();

    Resource createResource(Resource resource);
    List<Resource> getAllResources();

    String assignResourceToTask(Long taskId, Long resourceId);

    Project updateProject(Long projectId, Project project);
    Task updateTask(Long taskId, Task task);
    Resource updateResource(Long resourceId, Resource resource);
}
