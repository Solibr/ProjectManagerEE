package mapper;

import dto.ProjectDto;
import entity.Project;
import org.modelmapper.ModelMapper;

public class ProjectMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProjectDto dtoToEntity(Project project) {
        ProjectDto dto = modelMapper.map(project, ProjectDto.class);
        return dto;
    }
}
