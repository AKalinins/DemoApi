package lv.kalinins.demoapi.controller.mapper;

public interface Mapper<O, D, R> {

    R convertToResponseDto(O entity);
    O convertToEntity(D dto);
}
