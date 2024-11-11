package com.projectManagement.service.impl;


import com.projectManagement.model.*;
import com.projectManagement.repository.ProjectRepository;
import com.projectManagement.repository.ResourceRepository;
import com.projectManagement.repository.TaskAssignmentRepository;
import com.projectManagement.repository.TaskRepository;
import com.projectManagement.service.PmsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PmsServiceImpl implements PmsService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private TaskAssignmentRepository taskAssignmentRepository;


    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public String deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
        return "Project deleted successfully";
    }

    @Override
    public Project updateProject(Long projectId, Project project) {
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        existingProject.setProjectName(project.getProjectName());
        existingProject.setProjectDescription(project.getProjectDescription());


        if (project.getTasks() != null) {
            for (Task updatedTask : project.getTasks()) {
                Task existingTask = taskRepository.findById(updatedTask.getTaskId())
                        .orElseThrow(() -> new RuntimeException("Task not found"));
                existingTask.setTaskName(updatedTask.getTaskName());
                existingTask.setStatus(updatedTask.getStatus());
                taskRepository.save(existingTask);
            }
        }
        return projectRepository.save(existingProject);
    }


    @Override
    public Task updateTask(Long taskId, Task task) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setTaskName(task.getTaskName());
        existingTask.setStatus(task.getStatus());
        return taskRepository.save(existingTask);
    }


    @Override
    public Resource updateResource(Long resourceId, Resource resource) {
        Resource existingResource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        existingResource.setResourceName(resource.getResourceName());
        existingResource.setStatus(resource.getStatus());
        return resourceRepository.save(existingResource);
    }

    @Override
    public Task createTask(Task task, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        task.setProject(project);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    @Override
    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }


    @Override
    @Transactional
    public String assignResourceToTask(Long taskId, Long resourceId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));


        if (resource.getStatus() != ResourceStatus.AVAILABLE) {
            return "Resource is not available for assignment.";
        }


        long assignedTasks = taskAssignmentRepository.countByResourceAndTask_Project(resource, task.getProject());
        if (assignedTasks >= 2) {
            return "Resource is over-allocated. Cannot assign to more than 2 tasks in the same project.";
        }


        TaskAssignment assignment = new TaskAssignment();
        assignment.setTask(task);
        assignment.setResource(resource);
        taskAssignmentRepository.save(assignment);


        resource.setStatus(ResourceStatus.BUSY);
        resourceRepository.save(resource);

        return "Resource assigned to task successfully.";
    }


}
