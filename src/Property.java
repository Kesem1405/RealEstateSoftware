
public class Property {

    private User user;
    private City city;
    private String address;
    private String propertyType;
    private int propertyFloor;
    private int propertyNumber;
    private String propertyRooms;

    private int propertyPrice;

    //  O(1) - complexity
    private String propertyCategory;
    //  O(1) - complexity
    public String getPropertyRooms() {
        return propertyRooms;
    }
    //  O(1) - complexity
    public void setPropertyRooms(String propertyRooms) {
        this.propertyRooms = propertyRooms;
    }

    //  O(1) - complexity
    public void setUser(User user) {
        this.user = user;
    }
    //  O(1) - complexity
    public void setCity(City city) {
        this.city = city;
    }
    //  O(1) - complexity
    public void setAddress(String address) {
        this.address = address;
    }

    //  O(1) - complexity
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    //  O(1) - complexity
    public void setPropertyFloor(int propertyFloor) {
        this.propertyFloor = propertyFloor;
    }

    //  O(1) - complexity
    public void setPropertyNumber(int propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    //  O(1) - complexity
    public void setPropertyCategory(String propertyCategory) {
        this.propertyCategory = propertyCategory;
    }

    //  O(1) - complexity
    public void setPropertyPrice(int propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    //  O(1) - complexity
    public User getUser() {
        return user;
    }
    //  O(1) - complexity
    public City getCity() {
        return city;
    }

    //  O(1) - complexity
    public String getAddress() {
        return address;
    }

    //  O(1) - complexity
    public String getPropertyType() {
        if (propertyType.equals(Def.APARTMENT)) {
            propertyType = "Regular apartment";
        } else if (propertyType.equals(Def.PENTHOUSE)) {
            propertyType = "Penthouse apartment";
        } else if (propertyType.equals(Def.PRIVATE_HOUSE)) {
            propertyType = "Private house";
        }

        return propertyType;
    }

    //  O(1) - complexity
    public int getPropertyFloor() {
        return propertyFloor;
    }

    //  O(1) - complexity
    public int getPropertyNumber() {
        return propertyNumber;
    }

    //  O(1) - complexity
    public String getPropertyCategory() {
        return propertyCategory;
    }

    //  O(1) - complexity
    public int getPropertyPrice() {
        return propertyPrice;
    }

    //  O(1) - complexity
    public Property() {

    }
    //  O(1) - complexity
    public String toString() {
        String output = this.city + " - " + getAddress()  + " St. " + getPropertyNumber() + ". \n";
        switch (this.propertyType) {
            case Def.APARTMENT -> output += "Regular apartment";
            case Def.PENTHOUSE -> output += "Penthouse apartment";
            case Def.PRIVATE_HOUSE -> output += "Private house";
        }
        output += ", ";
        if (this.propertyCategory.equals("For rent")) {
            output += "For rent";
        } else {
            output += "For sale";
        }

        output += "." + "\n"+ this.propertyRooms + " Rooms ,";
        if (!this.propertyType.equals(Def.PRIVATE_HOUSE)) {
            output += "Floor:" + this.propertyFloor + "." +"\n";
        }
        output += "Price: " + this.propertyPrice + Def.DOLLAR_SIGN + ".\n";
        if (this.user != null) {
            output += "Contact: " + this.user.getUserName() + "  Phone number: " + this.user.getUserPhoneNumber() + ". \n";
            if (user.isBroker) {
                output += "[ Real estate broker ]";
            } else {
                output += "[ Private seller ]";
            }
        }
        return output;
    }
}
