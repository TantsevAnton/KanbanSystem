package ru.rsreu.tancev0814.commands;

import ru.rsreu.tancev0814.commands.action.ActionBoardCreateCommand;
import ru.rsreu.tancev0814.commands.action.ActionTaskCreateCommand;
import ru.rsreu.tancev0814.commands.action.ActionUserCreateCommand;
import ru.rsreu.tancev0814.commands.page.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand(true, false);
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand(false, false);
        }
    },
    BOARD_CREATE {
        {
            this.command = new BoardCreateCommand(true, true);
        }
    },
    ACTION_BOARD_CREATE {
        {
            this.command = new ActionBoardCreateCommand(false, true);
        }
    },
    BOARD_OPENING {
        {
            this.command = new BoardOpeningCommand(true, true);
        }
    },
    BOARD_DELETE {
        {
            this.command = new BoardDeleteCommand(true, true);
        }
    },
    TASK_INFORMATION_OPENING {
        {
            this.command = new TaskInformationOpeningCommand(true, true);
        }
    },
    TASK_INFORMATION_CLOSING_AND_SAVING {
        {
            this.command = new TaskInformationClosingAndSavingCommand(true, true);
        }
    },
    ADD_TASK_OPENING {
        {
            this.command = new AddTaskOpeningCommand(true, true);
        }
    },
    ACTION_TASK_CREATE {
        {
            this.command = new ActionTaskCreateCommand(false, true);
        }
    },
    TASK_CREATE {
        {
            this.command = new TaskCreateCommand(true, true);
        }
    },
    ADD_USER_OPENING {
        {
            this.command = new AddUserOpeningCommand(true, true);
        }
    },
    ACTION_USER_CREATE {
        {
            this.command = new ActionUserCreateCommand(false, true);
        }
    },
    USER_CREATE {
        {
            this.command = new UserCreateCommand(true, true);
        }
    },
    USER_INFORMATION_OPENING {
        {
            this.command = new UserInformationOpeningCommand(true, true);
        }
    },
    USER_INFORMATION_EDIT {
        {
            this.command = new UserInformationEditCommand(true, true);
        }
    },
    USER_DELETE {
        {
            this.command = new UserDeleteCommand(true, true);
        }
    },
    USERS_LIST_FOR_MODERATOR_OPENING {
        {
            this.command = new UsersListForModeratorOpeningCommand(true, true);
        }
    },
    USERS_BLOCK_UNBLOCK {
        {
            this.command = new UsersBlockUnblockCommand(true, true);
        }
    },
    BOARD_BLOCK_UNBLOCK {
        {
            this.command = new BoardBlockUnblockCommand(true, true);
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
