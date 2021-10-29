function openPopUp() {
    document.getElementById('popUp').setAttribute('style', 'display: block');
};

function closePopUp() {
    document.getElementById('popUp').setAttribute('style', 'display: none');
};

document.getElementById("buttonCreateBoard").addEventListener("click", openPopUp);
document.getElementById("buttonClosePopUp").addEventListener("click", closePopUp);