package ru.rsreu.tancev0814.datalayer.data;

/**
 * User group.
 */
public enum UserGroup {

    ADMINISTRATOR {
        {
            this.setGroupName("Administrator");
        }
    },
    MODERATOR {
        {
            this.setGroupName("Moderator");
        }
    },
    USER {
        {
            this.setGroupName("User");
        }
    };

    /**
     * User group name.
     */
    private String groupName;

    /**
     * Get user group name.
     * @return User group name.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set user group name.
     * @param groupName User group name.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
