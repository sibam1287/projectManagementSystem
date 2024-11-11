package com.projectManagement.controller;

import com.projectManagement.model.Project;
import com.projectManagement.model.Task;
import com.projectManagement.model.Resource;
import com.projectManagement.service.PmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/PMS")
public class PmsController {


    @Autowired
    private PmsService pmsService;


    @PostMapping("/projects")
    public Project createProject(@RequestBody Project project) {
        return pmsService.createProject(project);
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return pmsService.getAllProjects();
    }

    @GetMapping("/projects/{projectId}")
    public Project getProjectById(@PathVariable Long projectId) {
        return pmsService.getProjectById(projectId);
    }

    @DeleteMapping("/projects/{projectId}")
    public String deleteProject(@PathVariable Long projectId) {
        return pmsService.deleteProject(projectId);
    }


    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task, @RequestParam Long projectId) {
        return pmsService.createTask(task, projectId);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return pmsService.getAllTasks();
    }


    @PostMapping("/resources")
    public Resource createResource(@RequestBody Resource resource) {
        return pmsService.createResource(resource);
    }

    @GetMapping("/resources")
    public List<Resource> getAllResources() {
        return pmsService.getAllResources();
    }


    @PostMapping("/assignResource")
    public String assignResourceToTask(@RequestParam Long taskId, @RequestParam Long resourceId) {
        return pmsService.assignResourceToTask(taskId, resourceId);
    }

    @PutMapping("/projects/{projectId}")
    public Project updateProject(@PathVariable Long projectId, @RequestBody Project project) {
        return pmsService.updateProject(projectId, project);
    }


    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        return pmsService.updateTask(taskId, task);
    }


    @PutMapping("/resources/{resourceId}")
    public Resource updateResource(@PathVariable Long resourceId, @RequestBody Resource resource) {
        return pmsService.updateResource(resourceId, resource);
    }
}