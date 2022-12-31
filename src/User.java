import java.util.Scanner;

public class User {

    public String userName;
    public String userPassword;

    public String userPhoneNumber;

    public boolean isBroker;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return this.userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isBroker() {
        return this.isBroker;
    }

    public void setBroker(boolean broker) {
        this.isBroker = broker;
    }
    public User() {

    }

    public boolean isPasswordValid(String userPassword) {
        boolean isPasswordValid = false;
        if (userPassword.length() >= Def.MINIMUM_PASSWORD_LENGTH) {
            if (userPassword.contains("%") || userPassword.contains("$") || userPassword.contains("_")) {
                for (int i = 0; i < userPassword.length(); i++) {
                    if (userPassword.charAt(i) >= '0' && userPassword.charAt(i) <= '9') {
                        isPasswordValid = true;
                        break;
                    }
                }
            }
        }
        return isPasswordValid;
    }

    public boolean isValidUserName(String userNameToAdd) {
        boolean valid = true;
        if (RealEstate.usersList.length > 0) {
            for (int i = 0; i < RealEstate.usersList.length; i++) {
                if (userNameToAdd.equals(RealEstate.usersList[i].getUserName())) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }

    public boolean isPhoneNumberValid(String userPhoneNumber) {
        boolean isPhoneNumberValidCheck = false;
        if (userPhoneNumber.charAt(0) == '0' && userPhoneNumber.charAt(1) == '5') {
            if (userPhoneNumber.length() == 10) {
                if (userPhoneNumber.matches("[0-9]+")) {
                    isPhoneNumberValidCheck = true;
                }
            }
        }
        return isPhoneNumberValidCheck;
    }


    //  O(1) - complexity
    public boolean checkUserInput(String isUserBroker) {
        Scanner scanner = new Scanner(System.in);
        boolean correctInput = false;
        while(!isUserBroker.equals("1") && !isUserBroker.equals("2")) {
            correctInput = true;
            System.out.println("Invalid input, try again.");
            isUserBroker = scanner.next();
        }
        return correctInput;
    }
    //  O(1) - complexity

    public String toString() {
        return "" +
                "User name =" + this.userName + '\n' +
                ", userPhoneNumber='" + this.userPhoneNumber + '\n' +
                ", userPassword='" + this.userPassword + '\n' +
                ", isBroker=" + this.isBroker +
                '}';
    }
}