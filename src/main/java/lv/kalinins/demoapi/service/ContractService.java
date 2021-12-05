package lv.kalinins.demoapi.service;

import lv.kalinins.demoapi.domain.Contract;
import lv.kalinins.demoapi.domain.enums.ContractType;
import lv.kalinins.demoapi.domain.enums.UserType;

import java.time.LocalDate;
import java.util.List;

public interface ContractService {

    Contract save(Contract contract);
    List<Contract> getBy(LocalDate startDate, ContractType type, String userName, UserType userType);
    List<Contract> getByUserId(long userId);
}
