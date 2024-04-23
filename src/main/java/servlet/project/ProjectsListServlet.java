package servlet.project;

import dto.ProjectDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.ProjectMapper;
import service.ProjectService;

import java.io.IOException;
import java.util.List;

@WebServlet("/projects")
public class ProjectsListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/projects-list.jsp");
        List<ProjectDto> projectsList = ProjectService.getProjectsList().stream()
                .map(ProjectMapper::dtoToEntity)
                .toList();
        req.setAttribute("projects-list", projectsList);
        requestDispatcher.forward(req, resp);
    }

}
