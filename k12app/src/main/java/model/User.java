package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private String username;

   private UserData userData;

    public User() {
    }

    public User(String username, UserData userData) {
        this.username = username;
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userData=" + userData +
                '}';
    }
}
