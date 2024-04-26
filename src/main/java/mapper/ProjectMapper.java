package mapper;

import dto.ProjectDto;
import entity.Project;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ProjectMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public ProjectDto entityToDto(Project project) {
        ProjectDto dto = modelMapper.map(project, ProjectDto.class);
        return dto;
    }

    public Project dtoToEntity(ProjectDto projectDto) {
        Project entity = modelMapper.map(projectDto, Project.class);
        return entity;
    }
}
