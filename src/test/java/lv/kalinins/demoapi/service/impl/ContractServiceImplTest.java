package lv.kalinins.demoapi.service.impl;

import lv.kalinins.demoapi.domain.Contract;
import lv.kalinins.demoapi.domain.enums.ContractType;
import lv.kalinins.demoapi.domain.enums.UserType;
import lv.kalinins.demoapi.repository.ContractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContractServiceImplTest {

    @InjectMocks
    private ContractServiceImpl target;

    @Mock
    private ContractRepository repository;

    /**
     * {@link ContractServiceImpl#save(Contract)}
     */
    @Test
    void testSave() {

        Contract contract = new Contract();

        when(repository.save(contract)).thenReturn(contract);

        Contract result = target.save(contract);

        assertSame(contract, result);
        verify(repository, times(1)).save(contract);
    }

    /**
     * {@link ContractServiceImpl#getBy(LocalDate, ContractType, String, UserType)}
     */
    @Test
    void testGetBy() {

        List<Contract> contracts = new ArrayList<>();

        LocalDate localDate = LocalDate.now();
        ContractType contractType = ContractType.GAS;
        String userName = "Name";
        UserType userType = UserType.PRIVATE;

        when(repository.findContractByStartDateTypeUserNameAndUserType(localDate, contractType, userName, userType))
                .thenReturn(contracts);

        List<Contract> result = target.getBy(localDate, contractType, userName, userType);

        assertSame(contracts, result);
        verify(repository, times(1))
                .findContractByStartDateTypeUserNameAndUserType(localDate, contractType, userName, userType);
    }

    /**
     * {@link ContractServiceImpl#getByUserId(long)}
     */
    @Test
    void shouldGetContractsByUserId() {

        List<Contract> contracts = new ArrayList<>();

        when(repository.findByUser_Id(1L)).thenReturn(contracts);

        List<Contract> result = target.getByUserId(1L);

        assertSame(contracts, result);
        verify(repository, times(1)).findByUser_Id(1L);
    }
}