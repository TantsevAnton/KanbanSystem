<%--
  Created by IntelliJ IDEA.
  User: A&N
  Date: 11.03.2020
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Add new task</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="css/styles_forms_adding_and_editing.css">
</head>
<body>
    <header>
        <h1>Adding new task</h1>
    </header>
    <main>
        <form action="controller" method="post">
            <div class="form-group">
                <label for="taskName">Task name:</label>
                <input type="text" class="form-control" name="taskName" id="taskName" placeholder="Enter task name">
            </div>
            <div class="form-group">
                <label for="taskDescription">Description:</label>
                <input type="textarea" class="form-control" name="taskDescription" id="taskDescription" placeholder="Enter task description">
            </div>
            <div class="form-group">
                <label for="taskPriority">Select task priority:</label>
                <select class="form-control" name="taskPriority" id="taskPriority">
                    <option value="High">High</option>
                    <option value="Medium">Medium</option>
                    <option value="Low">Low</option>
                </select>
            </div>
            <div class="form-group">
                <label for="taskWorker">Select task performer:</label>
                <select class="form-control" name="taskWorker" id="taskWorker">
                    <c:forEach items="${workerList}" var="worker">
                        <option value="${worker.id}">[${worker.login}] ${worker.lastName} ${worker.firstName}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="hidden" name="board_id" value="${board.id}"/>
            <button id="btnCreateTask" class="btn" name="command" value="action_task_create">Create task</button>
        </form>
    </main>
</body>
</html>
