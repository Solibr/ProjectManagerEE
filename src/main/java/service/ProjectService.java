package service;

import entity.Project;
import repository.ProjectRepository;

import java.util.List;

public class ProjectService {

    public static List<Project> getProjectsList() {
        return ProjectRepository.getAllProjects();
    }

    public static Project getProject(Long id) {
        return ProjectRepository.getProjectById(id);
    }

    public static List<Project> getSubprojectsForProjectWithId(Long id) {
        return ProjectRepository.getAllProjects();
    }

    public static Project saveNewProject(Project project) {
        project.setId(3L);
        System.out.println(project.toString());
        return project;
    }
}
