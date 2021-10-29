<%--
  Created by IntelliJ IDEA.
  User: A&N
  Date: 16.03.2020
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Administration</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="css/users_list.css">
    <script src="js/header.js" defer></script>
</head>
<body>
    <header>
        <h1>Kanban Board</h1>
        <a class="newUser" href="controller?command=add_user_opening">Create user</a>
        <div id="user">${user.login}</div>
    </header>
    <form id="menu" action="controller" method="post">
        <button name="command" value="logout">logout</button>
    </form>
    <main>
        <form>
            <table id="table">
                <tr id="header">
                    <th>login</th>
                    <th>last Name</th>
                    <th>first Name</th>
                    <th>user group</th>
                    <th>is blocked</th>
                    <th>is authorized</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr id="content_table">
                        <td><a href="controller?command=user_information_opening&user_login=${user.login}">${user.login}</a></td>
                        <td><a href="controller?command=user_information_opening&user_login=${user.login}">${user.lastName}</a></td>
                        <td><a href="controller?command=user_information_opening&user_login=${user.login}">${user.firstName}</a></td>
                        <td><a href="controller?command=user_information_opening&user_login=${user.login}">${user.userGroup.groupName}</a></td>
                        <td><a href="controller?command=user_information_opening&user_login=${user.login}">${user.isBlocked}</a></td>
                        <c:set var="isAuthorized" value="false" />
                        <c:forEach var="item" items="${AuthorizedUsersList}">
                            <c:if test="${item eq user}">
                                <c:set var="isAuthorized" value="true" />
                            </c:if>
                        </c:forEach>
                        <td><a href="controller?command=user_information_opening&user_login=${user.login}">${isAuthorized}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </main>
</body>
</html>