package dto;

import enums.UserType;

public class Lead extends User {
    public Lead(String id, String name) {
        super(id, name);
    }

    public UserType getType() {
        return UserType.LEAD;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                '}';
    }
}