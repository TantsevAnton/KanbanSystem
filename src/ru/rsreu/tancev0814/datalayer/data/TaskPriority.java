package ru.rsreu.tancev0814.datalayer.data;

/**
 * Task priority.
 */
public enum TaskPriority {

    HIGH {
        {
            this.setName("High");
        }
    },
    MEDIUM {
        {
            this.setName("Medium");
        }
    },
    LOW {
        {
            this.setName("Low");
        }
    };

    /**
     * Task priority name.
     */
    String name;

    /**
     * Get task priority name.
     * @return Task priority name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set task priority name.
     * @param name Task priority name.
     */
    public void setName(String name) {
        this.name = name;
    }

}
