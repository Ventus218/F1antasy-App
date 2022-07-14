package main;

public class User {

    public static final User loggedInUser = new User();
    private String username;

    public static void hasLoggedIn(String username) {
        if (User.loggedInUser.username != null) {
            Utils.crashWithMessage("\"hasLoggedIn\" can only be called once.");
        }
        User.loggedInUser.username = username;
    }

    public String getUsername() {
        if (this.username == null) {
            Utils.crashWithMessage("\"hasLoggedIn\" must be called first.");
        }
        return this.username;
    }
}
