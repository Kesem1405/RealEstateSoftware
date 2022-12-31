import java.util.Arrays;

public class City {
    public String city;
    public String district;
    public String[] streets;


    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String[] getStreets() {
        return streets;
    }

    public String toString() {
        return "" +
                "" + this.city + '\'';
    }

    //O(n) - Complexity
    public String toStringStreets() {
        return  Arrays.toString(streets);
    }


   //O(1) - Complexity
    public City(String city, String district, String[] streets) {
        this.city = city;
        this.district = district;
        this.streets = streets;
    }

}


