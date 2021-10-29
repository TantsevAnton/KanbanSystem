const user = document.querySelector('#user');
const menu = document.querySelector('#menu');

user.addEventListener('click', () => {menu.style.display = 'block'; event.stopPropagation();});
menu.addEventListener('click', () => {event.stopPropagation()});
document.addEventListener('click', () => menu.style.display = 'none');