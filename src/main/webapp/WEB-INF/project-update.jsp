<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Update project</title>
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
    <form action="<%= "/projects/" + request.getAttribute("id") %>" method="post">
      <label for="name">New name</label>
      <input type="text" name="name" id="name" value="<%= request.getAttribute("oldName") %>">
      <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
      <input type="submit" value="Submit">
    </form>
  </div>
</div>


</body>
</html>