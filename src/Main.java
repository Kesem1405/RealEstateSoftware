import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to my property app, What you want to do?");
        realEstateAction();
    }

    //O(1)
    public static void realEstateAction() {
        RealEstate realEstate = new RealEstate();
        Scanner scanner = new Scanner(System.in);
        String userSelection;
        User user = null;
        do {
            System.out.println("[1] - Create account");
            System.out.println("[2] - Login to account");
            System.out.println("[3] - Exit");
            userSelection = scanner.nextLine();
            switch (userSelection) {
                case Def.REGISTER:
                   realEstate.createUser();
                   if (user!=null) {
                       user = realEstate.userLogin();
                   }
                    break;
                case Def.LOGIN:
                    user = realEstate.userLogin();
                    if (user == null) {
                        System.out.println("Incorrect username or password! Returning to the main menu.");
                    }
                    else{
                        realEstate.userMainMenu(user);
                    }
                        break;
                case Def.EXIT:
                    return;
                default:
                    System.out.println("Invalid selection, try again.");
                    break;
            }
        }    while(user == null);
    }
}