package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;

public class UserAddingModel {
    @Autowired
    User user;
    @Autowired
    Authority authority;
    @Autowired
    String birthDateString;

    public String getBirthDateString() {
        return birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
