package mainObjects;

public class User {
    private static String login;
    private static String name;
    private static String type;
    private static String bio;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        User.type = type;
    }

    public static String getBio() {
        return bio;
    }

    public static void setBio(String bio) {
        User.bio = bio;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        User.login = login;
    }
}
