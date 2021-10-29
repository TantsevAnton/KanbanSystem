function openPopUp() {
    document.getElementById('popUp').setAttribute('style', 'display: block');
};

function closePopUp() {
    document.getElementById('popUp').setAttribute('style', 'display: none');
    document.getElementById("NewPassword").value = '';
};

function ChangePassword() {
    document.getElementById("password").value = document.getElementById("NewPassword").value;
    document.getElementById("NewPassword").value = '';
    document.getElementById('popUp').setAttribute('style', 'display: none');
}

document.getElementById("btnChangePassword").addEventListener("click", openPopUp);
document.getElementById("buttonClosePopUp").addEventListener("click", closePopUp);
document.getElementById("ChangePassword").addEventListener("click", ChangePassword);