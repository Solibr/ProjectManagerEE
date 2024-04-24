package servlet.project;

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
        PrintWriter pw = resp.getWriter();
        pw.println("/project/new GET");
        pw.println(req.getParameter("parent"));
        req.setAttribute("parent", req.getParameter("parent"));
        this.getServletContext().getRequestDispatcher("/project-new.jsp");
    }
}
