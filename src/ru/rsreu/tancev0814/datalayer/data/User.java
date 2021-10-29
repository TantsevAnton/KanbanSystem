package ru.rsreu.tancev0814.datalayer.data;

/**
 * User.
 */
public class User {

    /**
     * ID.
     */
    private int id;

    /**
     * Login.
     */
    private String login;

    /**
     * Password.
     */
    private String password;

    /**
     * Last name.
     */
    private String lastName;

    /**
     * First name.
     */
    private String firstName;

    /**
     * User group.
     */
    private UserGroup userGroup;

    /**
     * Is the user blocked.
     */
    private boolean isBlocked;

    /**
     * User constructor.
     * @param id User ID.
     * @param login User login.
     * @param password User password.
     * @param lastName User last name.
     * @param firstName User first name.
     * @param userGroup User group.
     * @param isBlocked Is the user blocked.
     */
    public User(int id, String login, String password, String lastName, String firstName, UserGroup userGroup, boolean isBlocked) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.isBlocked = isBlocked;
        this.userGroup = userGroup;
        this.firstName = firstName;
    }

    /**
     * Empty user constructor.
     */
    public User() {

    }

    /**
     * Get user ID.
     * @return User ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Set user ID.
     * @param id User ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get user login.
     * @return User login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set user login.
     * @param login User login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get user password.
     * @return User password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user password.
     * @param password User password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get user last name.
     * @return User last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set user last name.
     * @param lastName User last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Find out if the user is blocked.
     * @return True if the user is blocked; false otherwise.
     */
    public boolean getIsBlocked() {
        return isBlocked;
    }

    /**
     * Set whether the user is blocked.
     * @param isBlocked Is the user blocked.
     */
    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    /**
     * Get user first name.
     * @return User first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set user first name.
     * @param firstName User first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get user group.
     * @return User group.
     */
    public UserGroup getUserGroup() {
        return userGroup;
    }

    /**
     * Set user group.
     * @param userGroup User group.
     */
    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    /**
     * Comparison of the current object and the passed object.
     * @param o The object with which the comparison is made.
     * @return True if the current object fields and the passed object fields are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (isBlocked != user.isBlocked) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!firstName.equals(user.firstName)) return false;
        return userGroup == user.userGroup;
    }

    /**
     * Generation of current object's hash.
     * @return Object hash.
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + userGroup.hashCode();
        result = 31 * result + (isBlocked ? 1 : 0);
        return result;
    }

}
