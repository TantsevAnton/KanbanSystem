<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 29.03.2020
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Users list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="css/users_list.css">
</head>
<body>
    <header>
        <h1>Kanban Board</h1>
    </header>
    <main>
        <form action="controller" method="get">
            <table id="table">
                <tr id="header">
                    <th>login</th>
                    <th>last Name</th>
                    <th>first Name</th>
                    <th>is blocked</th>
                    <th>is authorized</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr id="content_table">
                        <td><span>${user.login}</span></td>
                        <td><span>${user.lastName}</span></td>
                        <td><span>${user.firstName}</span></td>
                        <td><input type="checkbox" name="blockedUsersID" value="${user.id}" <c:if test="${user.isBlocked}">checked="checked"</c:if>></td>
                        <c:set var="isAuthorized" value="false" />
                        <c:forEach var="item" items="${AuthorizedUsersList}">
                            <c:if test="${item eq user}">
                                <c:set var="isAuthorized" value="true" />
                            </c:if>
                        </c:forEach>
                        <td><span>${isAuthorized}</span></td>
                    </tr>
                </c:forEach>
            </table>
            <button class="applyChanges"  name="command" value="users_block_unblock">Apply changes</button>
        </form>
    </main>
</body>
</html>
