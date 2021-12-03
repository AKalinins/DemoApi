package lv.kalinins.demoapi.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
