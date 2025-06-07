package dto;

import enums.UserType;

import java.util.HashSet;
import java.util.Set;

public class Developer extends User {


    //store list projectIds which I have requested in past
    Set<String> set;

    public Developer(String id, String name) {
        super(id, name);
        this.set = new HashSet<>();
    }

    public Set<String> getProjectIds() {
        return set;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + this.getName() + '\'' +
                ", id='" + this.getId() + '\'' +
                ", projectIds='" + this.getProjectIds() + '\'' +
                '}';
    }

    public UserType getType() {
        return UserType.DEVELOPER;
    }


}