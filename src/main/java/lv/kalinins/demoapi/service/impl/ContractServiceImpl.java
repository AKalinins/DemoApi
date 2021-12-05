package lv.kalinins.demoapi.service.impl;

import lv.kalinins.demoapi.domain.Contract;
import lv.kalinins.demoapi.domain.enums.ContractType;
import lv.kalinins.demoapi.domain.enums.UserType;
import lv.kalinins.demoapi.repository.ContractRepository;
import lv.kalinins.demoapi.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> getBy(LocalDate startDate, ContractType type, String userName, UserType userType) {
        return contractRepository.findContractByStartDateTypeUserNameAndUserType(startDate, type, userName, userType);
    }

    @Override
    public List<Contract> getByUserId(long userId) {
        return contractRepository.findByUser_Id(userId);
    }

    @Autowired
    public void setContractRepository(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }
}
