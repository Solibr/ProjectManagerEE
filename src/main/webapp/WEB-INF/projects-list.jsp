<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Project</title>
    <link rel="stylesheet" href="/css/style.css">
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
    <div class="projects column">
        <div class="projects__header row">
            <h1>Root projects</h1>
            <a href="/projects/new" class="button" th:if="${isAdmin}">Add project</a>
        </div>
        <div class="projects__list column">

            <%@ page import="dto.ProjectDto, java.util.List" %>
            <%
                List<ProjectDto> projectsList = (List<ProjectDto>) request.getAttribute("projects-list");
                for (ProjectDto project : projectsList) {
                    out.println("<a href=\"/projects/" + project.getId() + "\">" + project.getName() + "</a>");
                }
            %>

            <div class="project" th:each="project : ${projects}">
                <a th:href="'/projects/' + ${project.id}" th:text="${project.name}"></a>
            </div>

        </div>
    </div>
</div>


</body>
</html>