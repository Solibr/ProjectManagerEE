package mapper;

import dto.ProjectDto;
import entity.Project;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ProjectMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public ProjectDto entityToDto(Project project) {
        return modelMapper.map(project, ProjectDto.class); // лучше напрямую модельмаппер использовать
    }

    public Project dtoToEntity(ProjectDto projectDto) {
        return modelMapper.map(projectDto, Project.class);
    }
}
