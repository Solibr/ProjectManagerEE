package servlet.project;

import dto.ProjectDto;
import entity.Project;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.ProjectMapper;
import service.ProjectService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/projects")
public class ProjectsListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/projects-list.jsp");
        List<ProjectDto> projectsList = ProjectService.getProjectsList().stream()
                .map(ProjectMapper::entityToDto)
                .toList();
        req.setAttribute("projects-list", projectsList);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println();

        String name = req.getParameter("name");
        Long parentId = Long.parseLong(req.getParameter("parentId"));

        ProjectDto projectDto = new ProjectDto();
        projectDto.setParentId(parentId);
        projectDto.setName(name);

        Project project = ProjectMapper.dtoToEntity(projectDto);
        Project savedProject = ProjectService.saveNewProject(project);

        Long id = savedProject.getId();

        resp.sendRedirect("/projects/" + id);
    }
}
