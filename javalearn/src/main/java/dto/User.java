package dto;

import enums.UserType;


public abstract class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getId() {
        return id;
    }

    public abstract UserType getType();
}