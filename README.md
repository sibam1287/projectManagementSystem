# Project Management System

## Overview
This is a simple project management system built with Spring Boot (Java 17) that allows users to create, update, and delete projects. Each project contains a list of tasks, with each task having a status (Pending, In Progress, Completed). The system also includes resource allocation features where developers (resources) can be assigned to specific tasks.

## Features
1. **Project Management**: Create, update, delete projects, and manage associated tasks.
2. **Task Management**: Each task has a status - Pending, In Progress, or Completed.
3. **Resource Allocation**: Assign developers (human resources) to specific tasks.
4. **Resource Availability**: Only resources with a status of "Available" can be assigned to tasks.
5. **Task Constraints**: Resources cannot be allocated to more than 2 tasks simultaneously within the same project.
6. **Validation**: Prevents over-allocation of resources by displaying an error if a resource is assigned to more than 2 tasks in a single project.

## Technologies Used
- **Backend**: Java 17, Spring Boot, Spring MVC (Model-View-Controller architecture)
- **Database**: MySQL

## Requirements
- Java 17
- Spring Boot
- MySQL Database

## Setup Instructions
1. **Clone the Repository**
   ```bash
   git clone https://github.com/sibam1287/projectManagementSystem.git
   cd projectManagementSystem
