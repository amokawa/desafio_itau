package web.support;

import java.util.Random;
import java.util.UUID;

public class RandomCustomer {
    String email;
    String firstName;
    String lastName;
    String password;
    String address;
    String city;
    int state;
    String postalCode;
    String country;
    String mobilePhone;
    String addressAlias;

    public RandomCustomer() {
        this(UUID.randomUUID() + "@email.com",
                "First",
                "Last",
                UUID.randomUUID().toString().substring(0,32),
                "Address",
                "City",
                1 + new Random().nextInt(50),
                String.valueOf((1 + new Random().nextInt(9)) * 10000 + new Random().nextInt(10000)),
                "United States",
                String.valueOf(System.currentTimeMillis()),
                ("Address alias " + UUID.randomUUID()).substring(0,32));
    }

    public RandomCustomer(String email, String password) {
        this(email, "", "", password, "", "", 0, "", "", "", "");
    }

    public RandomCustomer(String email,
                          String firstName,
                          String lastName,
                          String password,
                          String address,
                          String city,
                          int state,
                          String postalCode,
                          String country,
                          String mobilePhone,
                          String addressAlias) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.mobilePhone = mobilePhone;
        this.addressAlias = addressAlias;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getAddressAlias() {
        return addressAlias;
    }

    @Override
    public String toString() {
        return "RandomCustomer{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", addressAlias='" + addressAlias + '\'' +
                '}';
    }
}
