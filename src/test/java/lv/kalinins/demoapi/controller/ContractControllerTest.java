package lv.kalinins.demoapi.controller;

import lv.kalinins.demoapi.controller.dto.ContractInputDto;
import lv.kalinins.demoapi.controller.dto.ContractResponseDto;
import lv.kalinins.demoapi.controller.mapper.impl.ContractMapper;
import lv.kalinins.demoapi.domain.Contract;
import lv.kalinins.demoapi.domain.enums.ContractType;
import lv.kalinins.demoapi.domain.enums.UserType;
import lv.kalinins.demoapi.service.ContractService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContractControllerTest {

    @InjectMocks
    private ContractController target;

    @Mock
    private ContractService contractService;
    @Mock
    private ContractMapper mapper;

    /**
     * {@link ContractController#getContracts(LocalDate, ContractType, String, UserType)}
     */
    @Test
    void testGetContracts() {

        List<Contract> contracts = new ArrayList<>();
        contracts.add(new Contract());

        List<ContractResponseDto> responseDtoList = new ArrayList<>();
        responseDtoList.add(new ContractResponseDto());

        LocalDate localDate = LocalDate.now();
        ContractType contractType = ContractType.GAS;
        String userName = "Name";
        UserType userType = UserType.PRIVATE;

        when(contractService.getBy(localDate, contractType, userName, userType)).thenReturn(contracts);
        when(mapper.convertAllToResponseDto(contracts)).thenReturn(responseDtoList);

        List<ContractResponseDto> result = target.getContracts(localDate, contractType, userName, userType);

        assertSame(responseDtoList, result);
        verify(contractService, times(1)).getBy(localDate, contractType, userName, userType);
        verify(mapper, times(1)).convertAllToResponseDto(contracts);
    }

    /**
     * {@link ContractController#addContract(ContractInputDto)}
     */
    @Test
    void shouldAddContractToDatabase() {

        ContractInputDto inputDto = new ContractInputDto();
        inputDto.setUserId(1L);
        Contract contract = new Contract();
        ContractResponseDto responseDto = new ContractResponseDto();

        when(mapper.convertToEntity(inputDto)).thenReturn(contract);
        when(contractService.save(contract)).thenReturn(contract);
        when(mapper.convertToResponseDto(contract)).thenReturn(responseDto);

        ContractResponseDto result = target.addContract(inputDto);

        assertSame(responseDto, result);
        verify(contractService, times(1)).save(contract);
        verify(mapper, times(1)).convertToEntity(inputDto);
        verify(mapper, times(1)).convertToResponseDto(contract);
    }

    /**
     * {@link ContractController#addContract(ContractInputDto)}
     */
    @Test
    void shouldReturnBadRequestResponseInCaseOfBadRequest() {

        ContractInputDto inputDto = new ContractInputDto();
        inputDto.setUserId(1L);
        Contract contract = new Contract();

        when(mapper.convertToEntity(inputDto)).thenReturn(contract);
        when(contractService.save(contract)).thenThrow(DataIntegrityViolationException.class);

        ContractResponseDto result = null;
        ResponseStatusException exception = null;

        try {
            result = target.addContract(inputDto);
        } catch (ResponseStatusException e) {
            exception = e;
        }

        assertNull(result);
        assertNotNull(exception);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(contractService, times(1)).save(contract);
        verify(mapper, times(1)).convertToEntity(inputDto);
        verify(mapper, times(0)).convertToResponseDto(contract);
    }
}