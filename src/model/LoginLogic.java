package model;

public class LoginLogic {
    public static boolean execute(User user) {
        return user.getUserName().equals("kimiko") && user.getPassword().equals("kimiko");
    }
}
