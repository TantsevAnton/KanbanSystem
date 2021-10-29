document.getElementById("close-menu").addEventListener("click", closeMenu);

if (document.querySelector("#boardOwner").innerText === user.innerText || document.querySelector("#userType").innerText === "Moderator") {
    document.getElementById("form-taskName-editable").addEventListener("click", showEditField);
    document.getElementById("form-taskDescription-editable").addEventListener("click", showEditField);

    document.getElementById("form-edit-taskName").addEventListener("blur", hideEditField);
    document.getElementById("form-edit-taskDescription").addEventListener("blur", hideEditField);
}

function closeMenu() {
    if (document.querySelector("#boardOwner").innerText === user.innerText || document.querySelector("#userType").innerText === "Moderator") {
        var data = getDataFromOwnerOrModeratorEditMenu();
    } else {
        var data = getDataFromGuestEditMenu();
    }
    submitFORM('controller', 'post', {'command': 'task_information_closing_and_saving', 'taskName': data.name, 'taskDescription': data.description, 'priority': data.priority, 'workerName': data.workerName, 'status': data.taskStatus });
};

function getDataFromOwnerOrModeratorEditMenu() {
    var data = {};

    var priorityList = document.querySelector("#form-taskPriority-editable");
    var workerList = document.querySelector("#form-workerName-editable");
    var columnsList = document.querySelector("#form-status");

    data.id = document.querySelector("#form-taskID").innerText;
    data.name = document.querySelector("#form-taskName-editable").innerText;
    data.description = document.querySelector("#form-taskDescription-editable").innerText;
    data.priority = getDropdownValue(priorityList);
    data.workerName = getDropdownValue(workerList);
    data.taskStatus = getDropdownValue(columnsList);

    return data;
};

function getDataFromGuestEditMenu() {
    var data = {};

    var columnsList = document.querySelector("#form-status");

    data.id = document.querySelector("#form-taskID").innerText;
    data.name = document.querySelector("#form-taskName").innerText;
    data.description = document.querySelector("#form-taskDescription").innerText;
    data.priority = document.querySelector("#form-taskPriority").innerText;
    data.workerName = document.querySelector("#workerID").innerText;
    data.taskStatus = getDropdownValue(columnsList);

    return data;
};

function getDropdownValue(dropdownList) {
    var result;
    for (var i = 0; i < dropdownList.length; i++) {
        if (dropdownList.selectedIndex === i) {
            result = dropdownList[i].value;
        }
    }
    return result;
};

function showEditField(event) {
    var target = event.target;
    target.setAttribute("class", target.className + " closed");

    var editField = target.nextElementSibling;
    editField.setAttribute("class", editField.className.slice(0, -7));
    editField.focus();
    editField.value = target.innerText;
};

function hideEditField(event) {
    var target = event.target;
    target.setAttribute("class", target.className + " closed");

    var textField = target.previousElementSibling;
    textField.setAttribute("class", textField.className.slice(0, -7));

    var dataCorrectly = false;
    if (target.name === "taskName") {
        dataCorrectly = isCorrectTaskName(target.value) || (!isEmpty(target.value));
    } else if (target.name === "taskDescription") {
        dataCorrectly = isCorrectTaskDescription(target.value) || (!isEmpty(target.value));
    }

    if (dataCorrectly) {
        textField.innerHTML = target.value;
    }
};

function isCorrectTaskName(taskName) {
    var result = true;
    if (taskName.length > 100) {
        result = false;
    }

    return result;
};

function isCorrectTaskDescription(taskDescription) {
    var result = true;
    if (taskDescription.length > 1000) {
        result = false;
    }

    return result;
};

function isEmpty(str) {
    var result = false;

    if (str.length === 0) {
        result = true;
    }

    return result;
}

function submitFORM(path, method, params) {
    method = method || "post";

    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    form._submit_function_ = form.submit;

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
        }
    }

    document.body.appendChild(form);
    form._submit_function_();
}