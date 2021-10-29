package ru.rsreu.tancev0814.commands;

import javax.servlet.http.HttpServletRequest;

public abstract class ActionCommand {

    private boolean forward;

    private boolean authenticationRequired;

    protected ActionCommand(boolean forward, boolean authenticationRequired) {
        this.forward = forward;
        this.authenticationRequired = authenticationRequired;
    }

    public abstract String execute(HttpServletRequest request);

    public boolean getForward() {
        return forward;
    }

    public boolean isAuthenticationRequired() {
        return authenticationRequired;
    }
}
