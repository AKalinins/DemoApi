package lv.kalinins.demoapi.controller.mapper.impl;

import lv.kalinins.demoapi.controller.dto.ContractInputDto;
import lv.kalinins.demoapi.controller.dto.ContractResponseDto;
import lv.kalinins.demoapi.controller.mapper.Mapper;
import lv.kalinins.demoapi.domain.Contract;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper implements Mapper<Contract, ContractInputDto, ContractResponseDto> {

    private static final ModelMapper mapper = new ModelMapper();

    @Override
    public ContractResponseDto convertToResponseDto(Contract contract) {
        return mapper.map(contract, ContractResponseDto.class);
    }

    @Override
    public Contract convertToEntity(ContractInputDto dto) {
        dto.setType(dto.getType().toUpperCase());
        return mapper.map(dto, Contract.class);
    }
}
