package lv.kalinins.demoapi.controller.dto;

import lv.kalinins.demoapi.domain.enums.UserType;

public class UserDto {

    private long id;
    private String name;
    private UserType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
