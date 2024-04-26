package service;

import entity.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.ProjectRepository;

import java.util.List;

@ApplicationScoped
public class ProjectService {

    @Inject
    private ProjectRepository projectRepository;

    public List<Project> getRootProjectsList() {
        return projectRepository.findByParentIdEqualsNull();
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).get();
    }

    public List<Project> getSubprojectsForProjectWithId(Long id) {
        return projectRepository.findByParentId(id);
    }

    public void saveNewProject(Project project) {
        project.setId(null);
        projectRepository.save(project);
    }

    public void updateProject(Project project) {
        projectRepository.update(project);
    }

    public void deleteById(long projectId) {
        projectRepository.deleteById(projectId);
    }
}
