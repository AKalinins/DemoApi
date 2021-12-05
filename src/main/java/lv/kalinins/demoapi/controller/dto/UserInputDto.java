package lv.kalinins.demoapi.controller.dto;

import lv.kalinins.demoapi.domain.enums.UserType;

public class UserInputDto {

    private String name;
    private UserType type;

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
