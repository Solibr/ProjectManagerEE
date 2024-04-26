package servlet.project;

import dto.ProjectDto;
import entity.Project;
import jakarta.inject.Inject;
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

    @Inject
    private ProjectService projectService;

    @Inject
    private ProjectMapper projectMapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/projects-list.jsp");
        List<ProjectDto> projectsList = projectService.getRootProjectsList().stream()
                .map(projectMapper::entityToDto)
                .toList();
        req.setAttribute("projects-list", projectsList);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println();

        String name = req.getParameter("name");
        Long parentId;
        try {
             parentId = Long.parseLong(req.getParameter("parentId"));
        } catch (Exception e) {
            parentId = null;
        }

        ProjectDto projectDto = new ProjectDto();
        projectDto.setParentId(parentId);
        projectDto.setName(name);

        Project project = projectMapper.dtoToEntity(projectDto);
        projectService.saveNewProject(project);

        if (parentId == null) {
            resp.sendRedirect("/projects");
        } else {
            resp.sendRedirect("/projects/" + parentId);
        }


    }
}
