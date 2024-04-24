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
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@WebServlet("/projects/*")
public class ProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        try {
            String strNumber = Arrays.stream(req.getPathInfo().split("/")).filter(s -> !s.isBlank()).findFirst().get();
            long projectId = Integer.parseInt(strNumber);

            Project project = ProjectService.getProject(projectId);
            List<Project> subprojects = ProjectService.getSubprojectsForProjectWithId(projectId);

            ProjectDto projectDto = ProjectMapper.dtoToEntity(project);
            List<ProjectDto> subprojectsDto = subprojects.stream().map(ProjectMapper::dtoToEntity).toList();

            req.setAttribute("project", projectDto);
            //req.setAttribute("parentId", projectDto.getParentId());
            req.setAttribute("subprojects", subprojectsDto);

            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/project.jsp");
            requestDispatcher.forward(req, resp);

        } catch (NumberFormatException | NoSuchElementException e) {
            resp.setStatus(404);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("delete action, POST");

        try {
            List<String> pathElementsList = Arrays.stream(req.getPathInfo().split("/")).filter(s -> !s.isBlank()).toList();
            String strNumber = pathElementsList.get(0);
            long projectId = Integer.parseInt(strNumber);

            Long parentProjectId = ProjectService.getProject(projectId).getParentId();

            // TODO deletion

            if (parentProjectId != null) {
                resp.sendRedirect("/projects/" + parentProjectId);
            } else {
                resp.sendRedirect("/projects");
            }

        } catch (NumberFormatException | NoSuchElementException e) {
            resp.setStatus(404);
        }
    }

}
