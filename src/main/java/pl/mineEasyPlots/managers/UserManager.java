package pl.mineEasyPlots.managers;

import pl.mineEasyPlots.objects.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static final List<User> users = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    public static User getUser(String name) {
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        User u = new User();
        u.setName(name);
        users.add(u);
        return u;
    }
}
