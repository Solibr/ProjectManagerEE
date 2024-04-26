<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add project</title>
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
    <div class="add-form" style="padding: 100px;">
        <form action="/projects" method="post">
            <label for="name">Project name</label>
            <input type="text" name="name" id="name">
            <input type="hidden" name="parentId" value="<%= request.getAttribute("parentId") %>">
            <input type="submit" value="Submit">
        </form>
    </div>
</div>

</body>
</html>