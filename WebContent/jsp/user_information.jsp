<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 17.03.2020
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>User Information</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="css/styles_forms_adding_and_editing.css">
    <script src="js/user_information.js" defer></script>
</head>
<body>
    <header>
        <h1>User Information</h1>
    </header>
    <main>
        <form action="controller" method="post">
            <div class="form-group">
                <label for="userLogin">User login:</label>
                <input type="text" class="form-control" name="userLogin" id="userLogin" placeholder="Enter user login" value="${editedUser.login}">
            </div>
            <input type="hidden" name="password" id="password" value="${editedUser.password}">
            <div class="form-group">
                <label for="userGroup">Select user group:</label>
                <select class="form-control" name="userGroup" id="userGroup">
                    <option value="Administrator"<c:if test="${'Administrator' eq editedUser.userGroup.groupName}">selected="selected"</c:if>>Administrator</option>
                    <option value="Moderator"<c:if test="${'Moderator' eq editedUser.userGroup.groupName}">selected="selected"</c:if>>Moderator</option>
                    <option value="User"<c:if test="${'User' eq editedUser.userGroup.groupName}">selected="selected"</c:if>>User</option>
                </select>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input class="form-control" type="text" name="lastName" id="lastName" placeholder="Enter last name" value="${editedUser.lastName}">
            </div>
            <div class="form-group">
                <label for="firstName">First Name:</label>
                <input class="form-control" type="text" name="firstName" id="firstName" placeholder="Enter first name" value="${editedUser.firstName}">
            </div>
            <button id="btnApplyChanges" class="btn" name="command" value="user_information_edit">Apply changes</button>
            <button type="button" id="btnChangePassword" class="btn">Change password</button>
            <button id="btnDeleteUser" class="btn${disabled}" name="command" value="user_delete">Delete user</button>
        </form>
    </main>
    <div class="wrap-popup" id="popUp">
        <div class="popup-content">
            <h2>Password changing</h2>
            <button id="buttonClosePopUp"><i class="fas fa-times"></i></button>
            <form class="form-group">
                <label for="NewPassword">New password:</label>
                <input id="NewPassword" type="text" minlength="3" name="NewPassword">
                <button type="button" id="ChangePassword" name="command" value="">Change password</button>
            </form>
        </div>
    </div>
</body>
</html>
