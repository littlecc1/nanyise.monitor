package top.wello.health.session;

import top.wello.health.dao.UserDTO;

public class SessionHolder {

    private static ThreadLocal<UserDTO> local = new ThreadLocal<>();

    public static void putUser(UserDTO user) {
        local.set(user);
    }

    public static UserDTO getUser() {
        return local.get();
    }

    public static void remove() {
        local.remove();
    }
}
