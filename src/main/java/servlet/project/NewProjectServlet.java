package servlet.project;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/projects/new")
public class NewProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("parentId", req.getParameter("parentId"));
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/project-new.jsp");
        requestDispatcher.forward(req, resp);
    }
}
