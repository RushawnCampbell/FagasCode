package Security;

import Security.User.UserType;

public class Authenticator {

    public UserType Verify(String username, String password) {

        UserType result = UserType.NONE;

        if (password.equals("password")) {
            if (username.equals("ceo")) {
                result = UserType.CEO;
            } else if (username.equals("cashier")) {
                result = UserType.CASHIER;
            } else if (username.equals("secretary")) {
                result = UserType.SECRETARY;
            }
        }

        return result;
    }

}
