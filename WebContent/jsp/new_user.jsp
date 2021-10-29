<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 17.03.2020
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Add new user</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="css/styles_forms_adding_and_editing.css">
</head>
<body>
    <header>
        <h1>Adding new user</h1>
    </header>
    <main>
        <form action="controller" method="post">
            <div class="form-group">
                <label for="userLogin">User login:</label>
                <input type="text" class="form-control" name="userLogin" id="userLogin" placeholder="Enter user login">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="text" class="form-control" name="password" id="password" placeholder="Enter password">
            </div>
            <div class="form-group">
                <label for="userGroup">Select user group:</label>
                <select class="form-control" name="userGroup" id="userGroup">
                    <option value="Administrator">Administrator</option>
                    <option value="Moderator">Moderator</option>
                    <option value="User">User</option>
                </select>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input class="form-control" type="text" name="lastName" id="lastName" placeholder="Enter last name">
            </div>
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <input class="form-control" type="text" name="firstName" id="firstName" placeholder="Enter first name">
            </div>
            <button id="btnCreateUser" class="btn" name="command" value="action_user_create">Create User</button>
        </form>
    </main>
</body>
</html>
