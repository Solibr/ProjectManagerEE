package servlet.project;

import dto.ProjectDto;
import entity.Project;
import jakarta.enterprise.context.RequestScoped;
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
import java.util.Arrays;
import java.util.List;

@WebServlet("/projects/*")
public class ProjectServlet extends HttpServlet {

    @Inject
    private ProjectService projectService;

    @Inject
    private ProjectMapper projectMapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        List<String> pathParts = Arrays.stream(req.getPathInfo().split("/")).filter(s -> !s.isBlank()).toList();

        // GET
        if (pathParts.size() == 1) {
            Long projectId;
            try {
                String strNumber = pathParts.get(0);
                projectId = Long.parseLong(strNumber);

                Project project = projectService.getProject(projectId);
                List<Project> subprojects = projectService.getSubprojectsForProjectWithId(projectId);

                ProjectDto projectDto = projectMapper.entityToDto(project);
                List<ProjectDto> subprojectsDto = subprojects.stream().map(projectMapper::entityToDto).toList();

                req.setAttribute("project", projectDto);
                req.setAttribute("subprojects", subprojectsDto);

                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/project.jsp");
                requestDispatcher.forward(req, resp);

            } catch (NumberFormatException e) {
                resp.setStatus(404);
            }

        }

        // GET update-page
        else if (pathParts.size() > 1 && pathParts.get(1).equals("update")) {

            try {
                Long id = Long.parseLong(pathParts.get(0));
                String oldName = projectService.getProject(id).getName();

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

        List<String> pathParts = Arrays.stream(req.getPathInfo().split("/")).filter(s -> !s.isBlank()).toList();

        // DELETE
        if (pathParts.size() == 2) {
            if (pathParts.get(1).equals("delete")) {
                try {
                    String strNumber = pathParts.get(0);
                    long projectId = Integer.parseInt(strNumber);

                    Long parentProjectId = projectService.getProject(projectId).getParentId();

                    projectService.deleteById(projectId);

                    if (parentProjectId != 0) {
                        resp.sendRedirect("/projects/" + parentProjectId);
                    } else {
                        resp.sendRedirect("/projects");
                    }

                } catch (NumberFormatException e) {
                    resp.setStatus(404);
                }
            }
        }

        // UPDATE
        else if (pathParts.size() == 1) {
            try {
                String strNumber = pathParts.get(0);
                long projectId = Integer.parseInt(strNumber);

                ProjectDto projectDto = new ProjectDto();
                projectDto.setName(req.getParameter("name"));
                projectDto.setId(projectId);

                Project project = projectMapper.dtoToEntity(projectDto);
                projectService.updateProject(project);

                resp.sendRedirect("/projects/" + projectId);

            } catch (NumberFormatException e) {
                resp.setStatus(404);
            }
        } else {
            resp.setStatus(404);
        }
    }

}
