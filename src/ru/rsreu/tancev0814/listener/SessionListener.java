package ru.rsreu.tancev0814.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ru.rsreu.tancev0814.datalayer.data.AuthorizedUsersList;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        AuthorizedUsersList.AUTHORIZED_USERS_LIST.remove(sessionEvent.getSession().getAttribute("user"));
    }

}
