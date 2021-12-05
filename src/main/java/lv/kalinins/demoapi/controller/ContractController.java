package lv.kalinins.demoapi.controller;

import lv.kalinins.demoapi.controller.dto.ContractInputDto;
import lv.kalinins.demoapi.controller.dto.ContractResponseDto;
import lv.kalinins.demoapi.controller.mapper.impl.ContractMapper;
import lv.kalinins.demoapi.domain.Contract;
import lv.kalinins.demoapi.domain.User;
import lv.kalinins.demoapi.domain.enums.ContractType;
import lv.kalinins.demoapi.domain.enums.UserType;
import lv.kalinins.demoapi.service.ContractService;
import lv.kalinins.demoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contract")
public class ContractController {

    private ContractService contractService;
    private ContractMapper contractMapper;
    private UserService userService;

    @PostMapping("")
    public ContractResponseDto addContract(@RequestBody ContractInputDto contractDto) {

        Optional<User> optionalUser = userService.getById(contractDto.getUserId());

        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found");
        }

        Contract contract = contractMapper.convertToEntity(contractDto);
        contract.setUser(optionalUser.get());
        try {
            contract = contractService.save(contract);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid request body");
        }

        return contractMapper.convertToResponseDto(contract);
    }

    @GetMapping("")
    public List<ContractResponseDto> getContracts(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) ContractType type,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) UserType userType
    ) {
        List<Contract> contracts = contractService.getBy(startDate, type, userName, userType);
        return contractMapper.convertAllToResponseDto(contracts);
    }

    @Autowired
    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    @Autowired
    public void setContractMapper(ContractMapper contractMapper) {
        this.contractMapper = contractMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
