package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserData {
    private String user;
    private String password;
    private String name;
    private String identityId;
    private List<String> schoolId;

    public UserData(String user, String password, String name) {
        this.user = user;
        this.password = password;
        this.name = name;
    }

    public UserData(){}

    public UserData(String user,
                    String password,
                    String name,
                    String identityId,
                    List<String> schoolId) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.identityId = identityId;
        this.schoolId = schoolId;

    }

    @Override
    public String toString() {
        return "UserData{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", identityId='" + identityId + '\'' +
                ", schoolId='" + schoolId + '\'' +
                '}';
    }
}
