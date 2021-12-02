package Security;

public class User {
    public static enum UserType {
        CEO,
        SECRETARY,
        CASHIER,
        NONE
    };

    private String username;
    private String password;
    private UserType userType;

    public User(String userN, String passW) {
        username = userN;
        password = passW;
        userType = UserType.NONE;
    }

    public void setUserType(UserType userT) {
        userType = userT;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }
}
