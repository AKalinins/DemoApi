package lv.kalinins.demoapi.controller.dto;

import lv.kalinins.demoapi.domain.Contract;
import lv.kalinins.demoapi.domain.enums.UserType;

import java.util.List;

public class UserDto {

    private long id;
    private String name;
    private UserType type;
    private List<Contract> contracts;

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

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
