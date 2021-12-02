package lv.kalinins.demoapi.service.impl;

import lv.kalinins.demoapi.repository.ContractRepository;
import lv.kalinins.demoapi.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;

    @Autowired
    public void setContractRepository(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }
}
