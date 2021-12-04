package lv.kalinins.demoapi.controller.dto;

import lv.kalinins.demoapi.domain.enums.ContractType;

import java.time.LocalDate;

public class ContractResponseDto {

    private long id;
    private ContractType type;
    private LocalDate startDate;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
