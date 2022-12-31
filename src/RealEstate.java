import java.util.*;

public class RealEstate {
    public static User[] usersList = new User[0];
    private static Property[] propertyList = new Property[0];
    private final City[] cities;
    private boolean isBroker;

    //  O(1) - complexity
    public RealEstate() {
        cities = new City[]{
                new City("Ashdod", "Darom", new String[]{"Eli cohen", "Yona", "Yarkon","Havatzelet","David"}),
                new City("Netanya", "Sharon", new String[]{"Florentin", "David","Brosh"}),
                new City("Raanana", "Sharon", new String[]{"Narkis", "Nordeu","Sigalit"}),
                new City("Haifa", "North", new String[]{"Sokolov", "Rimon","Begin"}),
                new City("Tveria", "North", new String[]{"Kaplan", "Tamar","Malachi"}),
                new City("Eilat", "Darom", new String[]{"Remez", "Macabim","Dizingof"}),
                new City("Ashkelon", "Darom", new String[]{"Shapira", "Milrod","Alenbi"}),
                new City("Holon", "Center", new String[]{"Zait", "HaDekel","Yosef"}),
                new City("Yafo", "Center", new String[]{"Almog", "Macabim","King George"}),
                new City("Jerusalem", "East", new String[]{"Sapir", "Agor","Yirmiyahu"}),
        };
    }

    // O(n) - complexity
    public void createUser() {
        User newUser = new User();
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        newUser.isValidUserName(userName);
        while (!newUser.isValidUserName(userName)) {
            System.out.println("Your username already exists. try again");
            userName = scanner.next();
            newUser.isValidUserName(userName);
        }
        newUser.setUserName(userName);
        System.out.println("Enter a password: ");
        System.out.println("[Password must contain: % / $ / _ characters ,And minimum of 5 characters.]");
        String userPassword = scanner.nextLine();
        newUser.isPasswordValid(userPassword);
        while (!newUser.isPasswordValid(userPassword)) {
            System.out.println("Your password do not meet the requirements, try again!");
            userPassword = scanner.nextLine();
            newUser.isPasswordValid(userPassword);
        }
        newUser.setUserPassword(userPassword);
        System.out.print("Enter your phone number: ");
        String userPhoneNumber = scanner.nextLine();
        while (!newUser.isPhoneNumberValid(userPhoneNumber)) {
            System.out.println("Your phone number is invalid, try again!");
            userPhoneNumber = scanner.nextLine();
            newUser.isPhoneNumberValid(userPhoneNumber);
        }
        newUser.setUserPhoneNumber(userPhoneNumber);
        System.out.println("Are you a broker?");
        System.out.println("[1] - Yes.");
        System.out.println("[2] - No.");
        String userIsBroker = scanner.nextLine();
        boolean isUserInputValid = newUser.checkUserInput(userIsBroker);
        if (isUserInputValid) {
            switch (userIsBroker) {
                case Def.YES -> isBroker = true;
                case Def.NO -> isBroker = false;
                default -> {
                }

            }
            newUser.setBroker(isBroker);
        }
        addUser(usersList, newUser);
    }
    //  - complexity

    // O(1) - complexity

    private String getLoginUserName() {
        String loginUserName;
        System.out.print("Enter your sign-in username: ");
        Scanner scanner = new Scanner(System.in);
        loginUserName = scanner.nextLine();
        return loginUserName.toLowerCase();
    }

    // O(1) - complexity

    private String getLoginUserPassword() {
        String loginUserPassword;
        System.out.print("Enter your sign-in password: ");
        Scanner scanner = new Scanner(System.in);
        loginUserPassword = scanner.nextLine();
        return loginUserPassword;
    }

    //  O(1) - complexity
    public User userLogin() {
        String loginUserName = getLoginUserName();
        String loginUserPassword = getLoginUserPassword();
        for (int i = 0; i < usersList.length; i++) {
            if (usersList[i].getUserName().toLowerCase().equals(loginUserName)
                    && usersList[i].getUserPassword().equals(loginUserPassword)) {
                System.out.println("Login success! What you like to do?");
                return usersList[i];
            }
        }
        return null;
    }

    // O(1) - complexity
    public void menuPrint() {
        System.out.println("[1] - Post a new property");
        System.out.println("[2] - Remove a post of property");
        System.out.println("[3] - Show all property's");
        System.out.println("[4] - Show all property's posted by you");
        System.out.println("[5] - Search for a property");
        System.out.println("[6] - Logout and return to main menu");
    }


    // O(1) - complexity
    public void actionByUserChoose(int userSelection, User user) {
        switch (userSelection) {
            case Def.POST_PROPERTY -> {
                postNewProperty(user);
                userMainMenu(user);
            }
            case Def.REMOVE_PROPERTY -> {
                removeProperty(user);
                userMainMenu(user);
            }
            case Def.PRINT_ALL_PROPERTYS -> {
                printAllPropertys();
                userMainMenu(user);
            }
            case Def.PRINT_ALL_USER_PROPERTYS -> {
                printProperties(user);
                userMainMenu(user);
            }
            case Def.PROPERTY_SEARCH -> {
                search();
                userMainMenu(user);
            }
            case Def.LOG_OUT -> {
                System.out.println("Disconnected.");
                Main.realEstateAction();
            }
            default -> {
                System.out.println("Invalid selection, try again.");
                userMainMenu(user);
            }
        }
    }

    // O(n) - complexity
    private boolean postNewProperty(User user) {
        boolean isUserCanPostProperty;
        int counter = countUserPosts(user);
        isUserCanPostProperty = checkUserCount(user, counter);
        if (isUserCanPostProperty) {
            Property property = new Property();
            property.setUser(user);
            System.out.println("Enter the city you want to post property from the list");
            printCities();
            String userCityInput = getUserCity();
            int cityIndex = matchUserCity(userCityInput);
            while (cityIndex == Def.INVALID_VALUE || cityIndex != matchUserCity(userCityInput)) {
                System.out.println("The city you entered doesn't match any city, try again.");
                userCityInput = getUserCity();
                cityIndex = matchUserCity(userCityInput);
            }
            property.setCity(cities[cityIndex]);
            String userStreetInput = getUserCityAddress(cityIndex);
            String[] cityStreets = cities[cityIndex].getStreets();
            int userStreetIndex = matchUserCityAddress(userStreetInput, cityStreets);
            while (userStreetIndex == Def.INVALID_VALUE || !userStreetInput.toLowerCase().trim().equals(cities[cityIndex].getStreets()[userStreetIndex].toLowerCase())) {
                System.out.println("Invalid street, try again");
                userStreetInput = getUserCityAddress(cityIndex);
                userStreetIndex = matchUserCityAddress(userStreetInput, cityStreets);
            }
            property.setAddress(cities[cityIndex].getStreets()[userStreetIndex]);
            String userPropertyType = setPropertyType();
            if (userPropertyType.equals(Def.APARTMENT) || userPropertyType.equals(Def.PENTHOUSE)) {
                String propertyFloor = userPropertyFloor();
                property.setPropertyFloor(Integer.parseInt(propertyFloor));
                property.setPropertyType(userPropertyType);
            } else {
                property.setPropertyType(userPropertyType);
            }
            String propertyRooms = setNewPropertyRooms();
            property.setPropertyRooms(propertyRooms);
            String propertyAddressNumber = setNewPropertyNumber();
            property.setPropertyNumber(Integer.parseInt(propertyAddressNumber));
            String propertyCategorySelection = setPropertyTypeSelection();
            String propertyCategory = changePropertyCategory(Integer.parseInt(propertyCategorySelection));
            property.setPropertyCategory(propertyCategory);
            String propertyPrice = setNewPropertyPrice();
            property.setPropertyPrice(Integer.parseInt(propertyPrice));
            addProperty(propertyList, property);
        } else {
            System.out.println("You reached the maximum amount of property posts.");
        }
        return isUserCanPostProperty;
    }

    //  O(n) - complexity
    private void printCities() {
        for (int k = 0; k < cities.length; k++) {
            System.out.println(cities[k].getCity() + "," + " " + cities[k].getDistrict() + "," + " " + cities[k].toStringStreets());
        }
    }


    //  O(n) - complexity
    private Property[] removeProperty(User user) {
        Property[] userProperties = new Property[0];
        if (propertyList.length > 0) {
            int userPropertiesCounter = counterUserProperties(user);
            if (userPropertiesCounter > 0) {
                userProperties = getUserRemoveProperty(userPropertiesCounter);
            }
        }
        else{
            System.out.println("There is no propeties listed.");
        }
            return userProperties;
        }


    //O(n) - complexity
    private int counterUserProperties(User user){
        int index;
        int counter = 0;
        for (int i = 0; i < propertyList.length; i++) {
            index = 0;
            if (propertyList[i].getUser().equals(user)) {
                index = i;
                counter++;
                System.out.println("[" + counter + "]. " + propertyList[index]);
                System.out.println(Def.UNDERSCORE_SPACE);
            } else {
                System.out.println("You don't have any listed property.");
            }
        }
        return counter;
    }

    //O(n) - complexity
    private String userRemovePropertyInput(int counter){
        System.out.println("Choose the number of the property you want to remove");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.matches("[0-9]+") || Integer.parseInt(userInput) <= 0 || Integer.parseInt(userInput) > counter) {
            System.out.println("Invalid property number, try again.");
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    //  O(n) - complexity
    private Property[] getUserRemoveProperty(int counter){
        String userInput = userRemovePropertyInput(counter);
        int propertyIndex = Integer.parseInt(userInput) - 1;
        Property[] updatedPropertyList = new Property[propertyList.length - 1];
        int j = 0;
        for (int i = 0; i < propertyList.length; i++) {
            if (i != propertyIndex) {
                updatedPropertyList[j] = propertyList[i];
                j++;
            }
        }
        propertyList = updatedPropertyList;
        System.out.println("Property removed successfully.");
        return propertyList;
    }

    //  O(n) - complexity
    private void printAllPropertys() {
        if (propertyList.length != Def.EMPTY) {
            for (int i = 0; i < propertyList.length; i++) {
                System.out.println(propertyList[i].toString());
                System.out.println(Def.UNDERSCORE_SPACE);
            }
        } else {
            System.out.println("No propertys yet listed");
        }
    }

    //  O(n) - complexity
    private void printProperties(User user) {
        if (propertyList.length != Def.EMPTY) {
            for (int i = 0; i < propertyList.length; i++) {
                if (propertyList[i].getUser().equals(user)) {
                    System.out.println(propertyList[i].toString());
                    System.out.println(Def.UNDERSCORE_SPACE);
                } else {
                    System.out.println("You dont have any property's listed by you.");
                }
            }
        } else {
            System.out.println("There is no propertys listed yet.");
        }
    }

    // O(1) - complexity
    private Property[] search() {
        System.out.println("REMINDER: You can type [-999] to filter a value ");
        String listingType = searchRentOrSale();
        String propertyTypeInput = getSearchPropertyType();
        String propertyRoomsInput = getSearchPropertyRooms();
        Integer minimum = getMinimumPriceRange();
        Integer maximum = getMaximumPriceRange(minimum);
        Property[] filteredList;
        filteredList = searchByFilter(propertyList, listingType, minimum, maximum, propertyTypeInput, propertyRoomsInput);
        if (filteredList.length == Def.EMPTY) {
            System.out.println("No propertys found.");
        }
        return filteredList;
    }


    //O(n) - complexity
    private Property[] searchByFilter(Property[] propertyList, String listingType, Integer min, Integer max, String propertyTypeInput, String propertyRoomsInput) {
        Property[] filteredList = new Property[0];
        for (int i = 0; i < propertyList.length; i++) {
            if (listingType == null || propertyList[i].getPropertyCategory().equals(listingType) &&
                    propertyTypeInput.equals(Def.FILTERING_PARAMETER) || propertyList[i].getPropertyType().equals(propertyTypeInput) &&
                    propertyRoomsInput.equals(Def.FILTERING_PARAMETER) || propertyList[i].getPropertyRooms().equals(propertyRoomsInput) &&
                    (min == null || propertyList[i].getPropertyPrice() >= min) && (max == null || propertyList[i].getPropertyPrice() <= max)) {
                System.out.println(propertyList[i].toString());
                System.out.println(Def.UNDERSCORE_SPACE);
                filteredList = addProperty(filteredList, propertyList[i]);
            }
        }
        return filteredList;
    }


    // O(1) - complexity
    private Integer getMaximumPriceRange(Integer minimum) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Integer maximum;
        System.out.println("What is the maximum price range?");
        userInput = scanner.nextLine();
        checkMaximumPrice(userInput, minimum);
        while (!checkMaximumPrice(userInput, minimum)) {
            System.out.println("Invalid input, remember maximum price should be bigger than minimum.");
            userInput = scanner.nextLine();
            checkMaximumPrice(userInput, minimum);
        }
        if (!userInput.equals(Def.FILTERING_PARAMETER)) {
            maximum = Integer.parseInt(userInput);
        } else {
            maximum = -999;
        }
        return maximum;
    }


    // O(n) - complexity
    private boolean checkMaximumPrice(String userInput, Integer minimum) {
        boolean valid = false;
        if (userInput.matches("-?[0-9]+")) {
            if (Integer.parseInt(userInput) > minimum || Integer.parseInt(userInput) <= minimum && userInput.equals(Def.FILTERING_PARAMETER)) {
                valid = true;
            }
        }
        return valid;
    }

    // O(1) - complexity
    private Integer getMinimumPriceRange() {
        Scanner scanner = new Scanner(System.in);
        Integer minimum;
        String userInput;
        System.out.println("Enter the minimum price");
        userInput = scanner.nextLine();
        while (!userInput.matches("[0-9]+") && !userInput.equals(Def.FILTERING_PARAMETER)) {
            System.out.println("Only numbers.");
            userInput = scanner.nextLine();
        }
        minimum = -999;
        if (userInput.matches("[0-9]+")) {
            minimum = Integer.parseInt(userInput);
        }
        return minimum;
    }

    // O(1) - complexity

    private String getSearchPropertyRooms() {
        String propertyRooms;
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many rooms?");
        propertyRooms = scanner.nextLine();
        while (!propertyRooms.matches("-?[0-9]+") && !propertyRooms.equals(Def.FILTERING_PARAMETER)) {
            System.out.println("Invalid input, try again.");
            propertyRooms = scanner.nextLine();
        }
        return propertyRooms;
    }


    // O(n) - complexity

    private String getSearchPropertyType() {
        String propertyTypeInput;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the type of the property? ");
        System.out.println("[1] - Apartment || [2] - Penthouse apartment || [3] - Private house");
        propertyTypeInput = scanner.nextLine();
        boolean validPropertyType = isPropertyTypeValid(propertyTypeInput);
        if (validPropertyType) {

            propertyTypeInput = switch (propertyTypeInput) {
                case Def.APARTMENT -> "Regular apartment";
                case Def.PENTHOUSE -> "Penthouse apartment";
                case Def.PRIVATE_HOUSE -> "Private house";
                default -> propertyTypeInput;
            };
        }
        return propertyTypeInput;
    }

    // O(1)- complexity
    private boolean isPropertyTypeValid(String propertyTypeInput) {
        boolean isValid = true;
        while (!propertyTypeInput.matches("-?[0-9]+") || (!propertyTypeInput.equals("1") && !propertyTypeInput.equals("2") && !propertyTypeInput.equals("3") && !propertyTypeInput.equals("-999"))) {
            System.out.println("Invalid selection, try again.");
            isValid = false;
            Scanner scanner = new Scanner(System.in);
            propertyTypeInput = scanner.nextLine();
        }
        return isValid;
    }

    // O(n) - complexity
    private String searchRentOrSale() {
        String rentOrSale;
        String listingType = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("[1] - For sale || [2] - For rent");
        rentOrSale = scanner.nextLine();
        boolean validInput = checkValidInput(rentOrSale);
        if (validInput) {
            switch (rentOrSale) {
                case "1" -> listingType = "For sale";
                case "2" -> listingType = "For rent";
                case "-999" -> listingType = null;
            }
        }
        return listingType;
    }

    //O(1) - complexity
    private boolean checkValidInput(String rentOrSale) {
        boolean validInput = true;
        while (!rentOrSale.matches("-?[0-9]+") || (!rentOrSale.equals("1") && !rentOrSale.equals("2") && !rentOrSale.equals("-999"))) {
            validInput = false;
            System.out.println("Invalid input, try again!");
            Scanner scanner = new Scanner(System.in);
            rentOrSale = scanner.nextLine();
        }
        return validInput;
    }


    // O(1) - complexity
    public void userMainMenu(User user) {
        menuPrint();
        String userSelection;
        Scanner selectionScanner = new Scanner(System.in);
        userSelection = selectionScanner.nextLine();
        while (!userSelection.matches("[0-9]+")) {
            System.out.println("Invalid input, try again.");
            userSelection = selectionScanner.nextLine();
        }
        actionByUserChoose(Integer.parseInt(userSelection), user);
    }

    // O(n) - complexity
    public static User[] addUser(User[] originalUsersList, User user) {
        User[] newUsersList = new User[originalUsersList.length + 1];
        for (int i = 0; i < originalUsersList.length; i++) {
            newUsersList[i] = originalUsersList[i];
        }
        newUsersList[newUsersList.length - 1] = user;
        usersList = newUsersList;
        return usersList;
    }

    // O(n) - Complexity
    private static Property[] addProperty(Property[] properties, Property property) {
        Property[] newPropertyList = new Property[properties.length + Def.ONE];
        for (int i = 0; i < properties.length; i++) {
            newPropertyList[i] = properties[i];
        }
        newPropertyList[newPropertyList.length - Def.ONE] = property;
        propertyList = newPropertyList;
        return propertyList;
    }

    // O(1) - Complexity
    private String setNewPropertyPrice() {
        String propertyPrice;
        System.out.println("What is the price for the property?");
        Scanner scanner = new Scanner(System.in);
        propertyPrice = scanner.nextLine();
        while (!propertyPrice.matches("[0-9]+") || Integer.parseInt(propertyPrice) <= 0) {
            System.out.println("Invalid input, try again.");
            propertyPrice = scanner.nextLine();
        }
        return propertyPrice;
    }

    // O(1) - Complexity
    private int countUserPosts(User user) {
        int counter = 0;
        if (propertyList.length > Def.EMPTY) {
            for (int i = 0; i < propertyList.length; i++) {
                if (propertyList[i].getUser().equals(user)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    // O(n) - complexity
    private int matchUserCity(String userCityInput) {
        int index = -1;
        boolean findCity = false;
        for (int k = 0; k < cities.length && !findCity; k++) {
            if (userCityInput.equals(cities[k].getCity().toLowerCase())) {
                findCity = true;
                index = k;
            }
        }
        return index;
    }


    // O(1) - complexity
    private String getUserCity() {
        String userCityInput;
        System.out.println(" ");
        Scanner scanner = new Scanner(System.in);
        userCityInput = scanner.nextLine().toLowerCase();
        return userCityInput;
    }

    // O(n) - complexity
    private String getUserCityAddress(int cityIndex) {
        for(int i=0; i<cities[cityIndex].streets.length; i++){
            System.out.println(cities[cityIndex].getStreets()[i]);
        }
        String userStreetInput;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which street you want to post property from the list?");
        userStreetInput = scanner.nextLine().toLowerCase();
        return userStreetInput;
    }

    // O(n) - complexity
    private int matchUserCityAddress(String userCityStreetInput, String[] cityStreets) {
        int index = 0;
        for (int k = 0; k < cityStreets.length; k++) {
            if (userCityStreetInput.equals(cityStreets[k].toLowerCase())) {
                index = k;
            }
        }
        return index;
    }

    // O(1) - complexity
    private String setPropertyType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the type of the property?");
        System.out.println("[1] - Apartment");
        System.out.println("[2] - Penthouse apartment");
        System.out.println("[3] - Private house");
        String userPropertyType;
        userPropertyType = scanner.nextLine();
        while (!userPropertyType.matches("0-9]+") && !userPropertyType.equals("1") && !userPropertyType.equals("2") && !userPropertyType.equals("3")) {
            System.out.println("Invalid selection, try again.");
            userPropertyType = scanner.nextLine();
        }
        return userPropertyType;
    }

    // O(1) - complexity
    private String userPropertyFloor() {
        System.out.println("What floor is the apartment?");
        Scanner scanner = new Scanner(System.in);
        String propertyFloor;
        propertyFloor = scanner.nextLine();
        while (!propertyFloor.matches("[0-9]+")) {
            System.out.println("Invalid input, try again.");
            propertyFloor = scanner.nextLine();
        }
        return propertyFloor;
    }

    // O(1) - complexity
    private String setNewPropertyRooms() {
        String roomsInput;
        System.out.println("How many rooms in the property?");
        Scanner scanner = new Scanner(System.in);
        roomsInput = scanner.nextLine();
        while (!roomsInput.matches("[0-9]+")) {
            System.out.println("Invalid input, try again.");
            roomsInput = scanner.nextLine();
        }
        return roomsInput;
    }

    // O(1) - complexity
    private boolean checkUserCount(User user, int counter) {
        boolean isUserCanPost = false;
        if (user.isBroker()) {
            if (counter < Def.BROKER_PROPERTY_LIMIT) {
                isUserCanPost = true;
            }
        } else {
            if (counter < Def.USER_PROPERTY_LIMIT) {
                isUserCanPost = true;
            }
        }
        return isUserCanPost;
    }

    // O(1) - complexity
    private String setNewPropertyNumber() {
        String propertyNumber;
        System.out.println("What is the number of the property?");
        Scanner scanner = new Scanner(System.in);
        propertyNumber = scanner.nextLine();
        while (!propertyNumber.matches("[0-9]+")) {
            System.out.println("Invalid input, try again.");
            propertyNumber = scanner.nextLine();
        }
        return propertyNumber;
    }

    // O(1) - complexity
    private String setPropertyTypeSelection() {
        String propertyCategorySelection;
        System.out.println("Is the property for sale or for rent?");
        System.out.println("[1] - For sale");
        System.out.println("[2] - For rent");
        Scanner scanner = new Scanner(System.in);
        propertyCategorySelection = scanner.nextLine();
        while (!propertyCategorySelection.matches("[0-9]+") || !propertyCategorySelection.equals("1") && !propertyCategorySelection.equals("2")) {
            System.out.println("Invalid input, try again.");
            propertyCategorySelection = scanner.nextLine();
        }
        return propertyCategorySelection;
    }

    // O(1) - complexity
    private String changePropertyCategory(int propertyCategoryInput) {
        String propertyCategory = "";
        if (propertyCategoryInput == Def.FOR_SALE) {
            propertyCategory = "For sale";
        } else if (propertyCategoryInput == Def.FOR_RENT) {
            propertyCategory = "For rent";
        }
        return propertyCategory;
    }
}
