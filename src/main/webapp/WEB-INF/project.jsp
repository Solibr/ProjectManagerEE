<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Project</title>
    <link rel="stylesheet" href="/css/style.css">

    <%@ page import="java.util.List, dto.ProjectDto" %>
    <% ProjectDto project = (ProjectDto) request.getAttribute("project"); %>
    <% List<ProjectDto> subprojects = (List<ProjectDto>) request.getAttribute("subprojects"); %>

</head>
<body>
<div class="header row">

    <div class="header__left-part row">
        <div class="header__home-button button">
            <a href="/">Home</a>
        </div>
    </div>

</div>
<div class="content">
    <div class="project row">

        <div class="project-text">
            <h1><%= project.getName() %></h1>
        </div>

        <% if (project.getParentId() != 0) { %>
        <div class="project-actions__to-parent">
            <a class="button" href="<%= "/projects/" + project.getParentId() %>">To parent project</a>
        </div>
        <% } %>

        <div class="project-actions__rename">
            <a class="button" href="<%= "/projects/" + project.getId() + "/update" %>">Rename</a>
        </div>

        <div class="project-actions__delete">
            <form action="<%= "/projects/" + project.getId() + "/delete" %>" method="post">
                <input type="submit" value="Delete">
            </form>
        </div>

    </div>
    <div class="project-content row">
        <div class="subprojects content">
            <div class="subprojects__header row">
                <div class="subprojects__label">
                    <h2>Subprojects</h2>
                </div>
                <div class="subprojects__new-button">
                    <a href="<%= "/projects/new?parentId=" + project.getId() %>" class="button">Add subproject</a>
                </div>
            </div>


            <div class="subprojects__listarea">

                <% for (ProjectDto subproject : subprojects) { %>

                <div th:each="subproject : ${subprojects}">
                    <a href="<%= "/projects/" + subproject.getId() %>" > <%= subproject.getName() %> </a>
                </div>

                <% } %>

            </div>
            <div class="subprojects__new"></div>
        </div>

    </div>

</div>
</body>
</html>