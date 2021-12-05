package lv.kalinins.demoapi.controller.mapper.impl;

import lv.kalinins.demoapi.controller.dto.ContractInputDto;
import lv.kalinins.demoapi.controller.dto.ContractResponseDto;
import lv.kalinins.demoapi.domain.Contract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContractMapperTest {

    @InjectMocks
    private ContractMapper target;

    @Mock
    private ModelMapper mapper;

    /**
     * {@link ContractMapper#convertToEntity(ContractInputDto)}
     */
    @Test
    void shouldMapInputDTOToEntity() {

        ContractInputDto dto = new ContractInputDto();
        target.convertToEntity(dto);

        verify(mapper, times(1)).map(dto, Contract.class);
    }

    /**
     * {@link  ContractMapper#convertToResponseDto(Contract)}
     */
    @Test
    void shouldMapEntityToResponseDTO() {

        Contract entity = new Contract();
        target.convertToResponseDto(entity);

        verify(mapper, times(1)).map(entity, ContractResponseDto.class);
    }

    /**
     * {@link ContractMapper#convertAllToResponseDto(List)}
     */
    @Test
    void shouldMapAllEntitiesToResponseDTOs() {

        List<Contract> entities = new ArrayList<>();
        entities.add(new Contract());
        entities.add(new Contract());

        target.convertAllToResponseDto(entities);

        verify(mapper, times(2)).map(any(Contract.class), ArgumentMatchers.<Class<ContractResponseDto>>any());
    }
}