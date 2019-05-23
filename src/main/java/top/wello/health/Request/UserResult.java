package top.wello.health.Request;

import top.wello.health.dao.UserDTO;

public class UserResult {

    private UserDTO person;
    private boolean newUser;

    public UserResult() {
    }

    public UserResult(UserDTO person, boolean newUser) {
        this.person = person;
        this.newUser = newUser;
    }

    public UserResult(UserDTO person) {
        this.person = person;
        this.newUser = false;
    }

    public UserDTO getPerson() {
        return person;
    }

    public void setPerson(UserDTO person) {
        this.person = person;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }
}
