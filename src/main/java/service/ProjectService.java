package service;

import entity.Project;
import repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    public static List<Project> getProjectsList() {
        return ProjectRepository.getAllProjects();
    }

    public static Project getOneProject() {
        return ProjectRepository.getOneProject();
    }

}
