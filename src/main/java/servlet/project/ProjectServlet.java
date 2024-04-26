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

@WebServlet("/projects/*")
public class ProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        // TODO get and update-page separation
        List<String> pathParts = Arrays.stream(req.getPathInfo().split("/")).filter(s -> !s.isBlank()).toList();
        if (pathParts.size() == 1) {
            Long projectId;
            try {
                String strNumber = pathParts.get(0);
                projectId = Long.parseLong(strNumber);

                Project project = ProjectService.getProject(projectId);
                List<Project> subprojects = ProjectService.getSubprojectsForProjectWithId(projectId);

                ProjectDto projectDto = ProjectMapper.entityToDto(project);
                List<ProjectDto> subprojectsDto = subprojects.stream().map(ProjectMapper::entityToDto).toList();

                req.setAttribute("project", projectDto);
                req.setAttribute("subprojects", subprojectsDto);

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/project.jsp");
                requestDispatcher.forward(req, resp);

            } catch (NumberFormatException e) {
                resp.setStatus(404);
            }

        } else if (pathParts.size() > 1 && pathParts.get(1).equals("update")) {

            try {
                Long id = Long.parseLong(pathParts.get(0));
                String oldName = ProjectService.getProject(id).getName();

                req.setAttribute("oldName", oldName);
                req.setAttribute("id", id);
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/project-update.jsp");
                requestDispatcher.forward(req, resp);
            } catch (NumberFormatException e) {
                resp.setStatus(404);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();


        // TODO delete and update separation
        List<String> pathParts = Arrays.stream(req.getPathInfo().split("/")).filter(s -> !s.isBlank()).toList();

        if (pathParts.size() == 2) {
            if (pathParts.get(1).equals("delete")) {
                try {
                    String strNumber = pathParts.get(0);
                    long projectId = Integer.parseInt(strNumber);

                    Long parentProjectId = ProjectService.getProject(projectId).getParentId();

                    // TODO deletion
                    pw.println("delete project with id: " + projectId);

/*                    if (parentProjectId != null) {
                        resp.sendRedirect("/projects/" + parentProjectId);
                    } else {
                        resp.sendRedirect("/projects");
                    }*/

                } catch (NumberFormatException e) {
                    resp.setStatus(404);
                }
            }
        } else if (pathParts.size() == 1) {
            try {
                String strNumber = pathParts.get(0);
                long projectId = Integer.parseInt(strNumber);
                pw.println("update project with id: " + projectId);

                // TODO update

            } catch (NumberFormatException e) {
                resp.setStatus(404);
            }
        } else {
            resp.setStatus(404);
        }
    }

}
