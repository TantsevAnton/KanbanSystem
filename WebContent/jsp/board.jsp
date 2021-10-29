<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 23.02.2020
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Board</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="css/board.css">
    <script src="js/board.js" defer></script>
    <script src="js/header.js" defer></script>
</head>
<body>
    <header>
        <h1 class="boardName">${board.name}</h1>
        <h2 id="boardOwner">${board.owner.login}</h2>
        <h2 id="userType">${user.userGroup.groupName}</h2>
        <c:if test="${board.owner == user}">
            <a class="newTask <c:if test="${user.isBlocked}">disabled</c:if>" href="controller?command=add_task_opening">New task</a>
        </c:if>
        <div id="user">${user.login}</div>
    </header>
    <form id="menu" action="controller" method="get">
        <button name="command" value="logout">logout</button>
    </form>
    <div class="container">
        <section class="content">
            <ul class="columns">
                <c:forEach var="column" items="${board.boardColumns}">
                    <li class="columns-column">
                            <span class="title">
                                <span class="numOfTasks">${fn:length(column.tasks)}</span>
                                <h3 class="columnName">${column.name}</h3>
                            </span>
                        <ul class="tasks">
                            <c:forEach var="task" items="${column.tasks}">
                                <li>
                                    <c:choose>
                                        <c:when test="${task.priority.name == 'High'}">
                                            <div class="task high">
                                                <c:if test="${board.owner != user && task.performer != user && user.userGroup.groupName != 'Moderator' || user.isBlocked}">
                                                    <p class="taskID">TASK-${task.id}</p>
                                                </c:if>
                                                <c:if test="${(board.owner == user|| task.performer == user || user.userGroup.groupName == 'Moderator') && !user.isBlocked}">
                                                    <a class="taskID" href="controller?command=task_information_opening&task_id=${task.id}&column_id=${column.id}">TASK-${task.id}</a>
                                                </c:if>
                                                <p class="taskName">${task.name}</p>
                                                <c:if test="${task.priorityChange.name == 'up'}">
                                                    <i class="changePriority fas fa-arrow-up"></i>
                                                </c:if>
                                                <c:if test="${task.priorityChange.name == 'down'}">
                                                    <i class="changePriority fas fa-arrow-down"></i>
                                                </c:if>
                                            </div>
                                        </c:when>
                                        <c:when test="${task.priority.name == 'Medium'}">
                                            <div class="task medium">
                                                <c:if test="${board.owner != user && task.performer != user && user.userGroup.groupName != 'Moderator' || user.isBlocked}">
                                                    <p class="taskID">TASK-${task.id}</p>
                                                </c:if>
                                                <c:if test="${(board.owner == user|| task.performer == user || user.userGroup.groupName == 'Moderator') && !user.isBlocked}">
                                                    <a class="taskID" href="controller?command=task_information_opening&task_id=${task.id}&column_id=${column.id}">TASK-${task.id}</a>
                                                </c:if>
                                                <p class="taskName" >${task.name}</p>
                                                <c:if test="${task.priorityChange.name == 'up'}">
                                                    <i class="changePriority fas fa-arrow-up"></i>
                                                </c:if>
                                                <c:if test="${task.priorityChange.name == 'down'}">
                                                    <i class="changePriority fas fa-arrow-down"></i>
                                                </c:if>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="task low">
                                                <c:if test="${board.owner != user && task.performer != user && user.userGroup.groupName != 'Moderator' || user.isBlocked}">
                                                    <p class="taskID">TASK-${task.id}</p>
                                                </c:if>
                                                <c:if test="${(board.owner == user|| task.performer == user || user.userGroup.groupName == 'Moderator') && !user.isBlocked}">
                                                    <a class="taskID" href="controller?command=task_information_opening&task_id=${task.id}&column_id=${column.id}">TASK-${task.id}</a>
                                                </c:if>
                                                <p class="taskName">${task.name}</p>
                                                <c:if test="${task.priorityChange.name == 'up'}">
                                                    <i class="changePriority fas fa-arrow-up"></i>
                                                </c:if>
                                                <c:if test="${task.priorityChange.name == 'down'}">
                                                    <i class="changePriority fas fa-arrow-down"></i>
                                                </c:if>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </section>
        <section class="edit-menu${visibilityEditMenu}">
            <button id="close-menu"><i class="fas fa-times"></i></button>
            <form class="form-edit-menu">
                <div class="form-group">
                    <label for="form-taskID">Task ID:</label>
                    <p class="form-control" id="form-taskID">${editedTask.id}</p>
                </div>
                <div class="form-group">
                    <label for="form-edit-taskName">Task name:</label>
                    <c:if test="${board.owner == user || user.userGroup.groupName == 'Moderator'}">
                        <p class="form-control" id="form-taskName-editable">${editedTask.name}</p>
                    </c:if>
                    <c:if test="${board.owner != user && user.userGroup.groupName != 'Moderator'}">
                        <p class="form-control" id="form-taskName">${editedTask.name}</p>
                    </c:if>
                    <input class="closed" type="text" name="taskName" id="form-edit-taskName">
                </div>
                <div class="form-group">
                    <label for="form-edit-taskDescription">Task description:</label>
                    <c:if test="${board.owner == user || user.userGroup.groupName == 'Moderator'}">
                        <p class="form-control" id="form-taskDescription-editable">${editedTask.description}</p>
                    </c:if>
                    <c:if test="${board.owner != user && user.userGroup.groupName != 'Moderator'}">
                        <p class="form-control" id="form-taskDescription">${editedTask.description}</p>
                    </c:if>
                    <textarea class="closed" name="taskDescription" id="form-edit-taskDescription" cols="30" rows="5"></textarea>
                </div>
                <div class="form-group">
                    <label for="form-taskPriority">Select task priority:</label>
                    <c:if test="${board.owner == user || user.userGroup.groupName == 'Moderator'}">
                        <select class="form-control" name="taskPriority" id="form-taskPriority-editable">
                            <c:forEach items="${priorityList}" var="priority">
                                <option value="${priority.name}" <c:if test="${priority.name eq editedTask.priority.name}">selected="selected"</c:if>>${priority.name}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${board.owner != user && user.userGroup.groupName != 'Moderator'}">
                        <c:forEach items="${priorityList}" var="priority">
                            <c:if test="${priority.name eq editedTask.priority.name}">
                                <p class="form-control" id="form-taskPriority">${priority.name}</p>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="form-workerName">Task performer:</label>
                    <c:if test="${board.owner == user || user.userGroup.groupName == 'Moderator'}">
                        <select class="form-control" name="workerName" id="form-workerName-editable">
                            <c:forEach items="${workerList}" var="worker">
                                <option value="${worker.id}" <c:if test="${worker.id eq editedTask.performer.id}">selected="selected"</c:if>>[${worker.login}] ${worker.lastName} ${worker.firstName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${board.owner != user && user.userGroup.groupName != 'Moderator'}">
                        <c:forEach items="${workerList}" var="worker">
                            <c:if test="${worker.id eq editedTask.performer.id}">
                                <p id="workerID">${worker.id}</p>
                                <p class="form-control" id="form-workerName">[${worker.login}] ${worker.lastName} ${worker.firstName}</p>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="form-status">Current status:</label>
                    <select class="form-control" name="form-status" id="form-status">
                        <c:forEach items="${statusList}" var="status">
                            <option value="${status.id}" <c:if test="${status.id eq status_id}">selected="selected"</c:if>>${status.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </form>
        </section>
    </div>
</body>
</html>
