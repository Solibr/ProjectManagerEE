package mapper;

import dto.ProjectDto;
import entity.Project;
import org.modelmapper.ModelMapper;

public class ProjectMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProjectDto entityToDto(Project project) {
        ProjectDto dto = modelMapper.map(project, ProjectDto.class);
        return dto;
    }

    public static Project dtoToEntity(ProjectDto projectDto) {
        Project entity = modelMapper.map(projectDto, Project.class);
        return entity;
    }
}
