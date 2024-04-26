package service;

import entity.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import repository.ProjectRepository;

import java.util.List;

@ApplicationScoped
public class ProjectService {

    @Inject
    private ProjectRepository projectRepository;

    public List<Project> getProjectsList() {
        return projectRepository.getAllProjects();
    }

    public Project getProject(Long id) {
        return projectRepository.getProjectById(id);
    }

    public List<Project> getSubprojectsForProjectWithId(Long id) {
        return projectRepository.getAllProjects();
    }

    public Project saveNewProject(Project project) {
        project.setId(3L);
        System.out.println(project.toString());
        return project;
    }
}
