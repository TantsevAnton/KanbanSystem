<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 14.02.2020
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Home page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/script.js" defer></script>
    <script src="js/header.js" defer></script>
</head>
<body>
    <header>
        <h1>Kanban Board</h1>
        <c:if test="${user.userGroup.groupName == 'User'}">
            <button id="buttonCreateBoard" class="<c:if test="${user.isBlocked}">disabled</c:if>">Create board</button>
        </c:if>
        <c:if test="${user.userGroup.groupName == 'Moderator'}">
            <a class="openUsersList" href="controller?command=users_list_for_moderator_opening">Open users list</a>
        </c:if>
        <div id="user">${user.login}</div>
    </header>
    <form id="menu" action="controller" method="get">
        <button name="command" value="logout">logout</button>
    </form>
    <ul class="boards">
        <c:forEach var="board" items="${boards}">
            <li id="${board.id}" class="boards__board">
                <form action="controller" method="get">
                    <input type="hidden" name="board_id" value="${board.id}"/>
                    <ul>
                        <li><button class="titleOfBoard <c:if test="${board.isBlocked && user.userGroup.groupName == 'User'}">disabled</c:if>" name="command" value="board_opening">${board.name}</button></li>
                        <c:if test="${board.owner == user || user.userGroup.groupName == 'Moderator'}">
                            <li><button class="buttonDeleteBoard <c:if test="${user.isBlocked}">disabled</c:if>" name="command" value="board_delete">Delete board</button></li>
                        </c:if>
                        <c:if test="${user.userGroup.groupName == 'Moderator'}">
                            <c:if test="${!board.isBlocked}">
                                <li><button class="blockBoard" name="command" value="board_block_unblock">Block board</button></li>
                            </c:if>
                            <c:if test="${board.isBlocked}">
                                <li><button class="unblockBoard" name="command" value="board_block_unblock">Unblock board</button></li>
                            </c:if>
                        </c:if>
                        <c:if test="${board.owner != user || user.userGroup.groupName == 'Moderator'}">
                            <li class="boardOwner">Board Owner: [${board.owner.login}] ${board.owner.lastName} ${board.owner.firstName}</li>
                        </c:if>
                        <li>Creation Date: <date class="creationDate">${board.creationDate}</date></li>
                        <li>Modification Date: <date class="editDate">${board.modificationDate}</date></li>
                    </ul>
                </form>
            </li>
        </c:forEach>
    </ul>
    <div class="wrap-popup" id="popUp">
        <div class="popup-content">
            <h2>New board creating</h2>
            <button id="buttonClosePopUp"><i class="fas fa-times"></i></button>
            <form class="form-group" action="controller" method="post">
                <label for="titleOfNewBoard">New board name:</label>
                <input id="titleOfNewBoard" type="text" minlength="3" name="NewBoard">
                <button id="buttonCreate" name="command" value="action_board_create">Create</button>
            </form>
        </div>
    </div>
</body>
</html>
