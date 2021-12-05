package lv.kalinins.demoapi.controller.mapper.impl;

import lv.kalinins.demoapi.controller.dto.ContractInputDto;
import lv.kalinins.demoapi.controller.dto.ContractResponseDto;
import lv.kalinins.demoapi.controller.mapper.Mapper;
import lv.kalinins.demoapi.domain.Contract;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContractMapper implements Mapper<Contract, ContractInputDto, ContractResponseDto> {

    private ModelMapper modelMapper;

    @Override
    public ContractResponseDto convertToResponseDto(Contract contract) {
        return modelMapper.map(contract, ContractResponseDto.class);
    }

    @Override
    public Contract convertToEntity(ContractInputDto dto) {
        return modelMapper.map(dto, Contract.class);
    }

    @Override
    public List<ContractResponseDto> convertAllToResponseDto(List<Contract> entities) {
        List<ContractResponseDto> result = new ArrayList<>();
        for (Contract entity : entities) {
            result.add(convertToResponseDto(entity));
        }
        return result;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
