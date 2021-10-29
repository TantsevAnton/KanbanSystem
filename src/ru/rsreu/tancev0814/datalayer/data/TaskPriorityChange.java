package ru.rsreu.tancev0814.datalayer.data;

/**
 * Task priority change.
 */
public enum TaskPriorityChange {

    UP {
        {
            this.setName("up");
        }
    },
    DOWN {
        {
            this.setName("down");
        }
    },
    NONE {
        {
            this.setName("none");
        }
    };

    /**
     * Task priority change name.
     */
    String name;

    /**
     * Get task priority change name.
     * @return Task priority change name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set task priority change name.
     * @param name Task priority change name.
     */
    public void setName(String name) {
        this.name = name;
    }

}
