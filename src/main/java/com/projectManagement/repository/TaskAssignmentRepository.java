package com.projectManagement.repository;

import com.projectManagement.model.Project;
import com.projectManagement.model.Resource;
import com.projectManagement.model.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Long> {
    long countByResourceAndTask_Project(Resource resource, Project project);
}
