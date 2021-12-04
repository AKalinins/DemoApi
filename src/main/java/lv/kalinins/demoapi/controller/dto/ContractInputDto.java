package lv.kalinins.demoapi.controller.dto;

import java.time.LocalDate;

public class ContractInputDto {

    private String type;
    private LocalDate startDate;
    private long userId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
